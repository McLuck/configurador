
package com.compsis.configurador.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Utilitário de arquivos
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 05/10/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public final class FilesUtil {
	private FilesUtil(){}
	
	/**
     * Tamanho do buffer
     */
    private static final int BUFFER_SIZE = 4096;
	
	/**
	 * Apagar diretório solicitado recursivamente <br>
	 * Irá apagar todos os arquivos e diretorios internos do diretório
	 * solicitado
	 * @param arquivo
	 * @param logger
	 */
	public static void apagarDiretorio(final File arquivo, final Logger logger) {
		if(arquivo!=null && arquivo.exists()) {
			if(arquivo.isFile()) {
				if(logger.isDebugEnabled()) {
					logger.debug("Apagando arquivo "+arquivo.getAbsolutePath());
				}
				arquivo.delete();
			} else {
				for (File oArquivo : arquivo.listFiles()) {
					apagarDiretorio(oArquivo, logger); 
				}
				arquivo.delete();
			}			
		}
	}
	
	/**
	 * Valida se diretorio informado existe. 
	 * Caso o diretorio não existe ou não tenha acesso de leitura e escrita, uma {@link RuntimeException}
	 * será lançada.
	 * @param arquivo
	 */
	public static void validarDiretorio(final File arquivo) {
		if(!arquivo.exists()) {
			throw new RuntimeException("Pasta infra, definida em "+ arquivo.getAbsolutePath()+", não existe");			
		}
		if(!arquivo.canWrite()) {
			throw new RuntimeException("Pasta infra, definida em "+ arquivo.getAbsolutePath()+", não está acessível para escrita");			
		}
		if(!arquivo.canRead()) {
			throw new RuntimeException("Pasta infra, definida em "+ arquivo.getAbsolutePath()+", não está acessível para leitura");			
		}
	}

	/**
	 * Adiciona a pasta no arquivo ZIP informado
	 * @param pastaRaiz pasta raiz que será compactada
	 * @param arquivoZip arquivo final (onde irá gerar o arquivo compactado)
	 */
	public static void zipDir(final File pastaRaiz, final File arquivoZip) {
		try {
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(arquivoZip));
			addDir(pastaRaiz, out, pastaRaiz.getParent());
			out.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	
	private static void addDir(File dirObj, ZipOutputStream out, String sourceDir) throws IOException {
		File[] files = dirObj.listFiles();
		byte[] tmpBuf = new byte[1024];

		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				addDir(files[i], out, sourceDir);
				continue;
			}
			FileInputStream in = new FileInputStream(files[i].getAbsolutePath());
			
			String path = files[i].getPath().substring(sourceDir.length());
		    if (path.startsWith(File.pathSeparator)) {
		        path = path.substring(1);
		    }
			out.putNextEntry(new ZipEntry(path));
			int len;
			while ((len = in.read(tmpBuf)) > 0) {
				out.write(tmpBuf, 0, len);
			}
			out.closeEntry();
			in.close();
		}
	}
	
	/**
	 * Extrai arquivo para o diretorio desejado
	 * @param zipFilePath Arquivo do tipo zip
	 * @param destDir pasta que se deseja extrais o arquivo
	 */
    public static void unzip(File zipFilePath, File destDir) {
    	try {
	        if (!destDir.exists()) {
	            destDir.mkdir();
	        }
	        ZipInputStream zipIn;
			zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
	        ZipEntry entry = zipIn.getNextEntry();
	        // iterates over entries in the zip file
	        while (entry != null) {
	            File filePath = new File(destDir, entry.getName());
	            if (!entry.isDirectory()) {
	                // if the entry is a file, extracts it
	                extractFile(zipIn, filePath);
	            } else {
	                // if the entry is a directory, make the directory
	                filePath.mkdirs();
	            }
	            zipIn.closeEntry();
	            entry = zipIn.getNextEntry();
	        }
	        zipIn.close();
    	} catch (IOException e) {
    		throw new RuntimeException(e);
    	}
    }
    /**
     * Extracts a zip entry (file entry)
     * @param zipIn
     * @param filePath
     * @throws IOException
     */
    private static void extractFile(ZipInputStream zipIn, File filePath) throws IOException {
    	if(!filePath.getParentFile().exists()) {
    		filePath.getParentFile().mkdirs();
    	}
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        byte[] bytesIn = new byte[BUFFER_SIZE];
        int read = 0;
        while ((read = zipIn.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
        }
        bos.close();
    }
    
	public static void main(String[] args) {
//		zipDir(new File("f:\\temp"), new File("f:\\test.zip"));
		unzip(new File("f:\\test.zip"), new File("f:\\teste"));
	}
}
