
package com.compsis.configurador.dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.google.common.base.Strings;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Wrapper para coneão com banco de dados
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 03/10/2015 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ConnectionWrapper {
	private static ConnectionWrapper me = new ConnectionWrapper();

    private String driverClassName = null;
    public String dataBaseName = "";
    private String databaseparameters = "databaseparameters.properties";
    private Context ctx = null;
    private String datasourceName;

    private static boolean emTestes = false;
    

    /* (non-Javadoc)
	 * @see br.com.compsisnet.j2eesmartlib.dao.IConnectionWrapper#setDatabaseParameter(java.lang.String)
	 */
	public void setDatabaseParameter(String dp) {
        databaseparameters = dp;
    }

    
	private ConnectionWrapper() {

        try {
        	String hostDataSource = System.getProperty("hostDataSource");
        	String dataSource = System.getProperty("dataSource");
        	if(Strings.isNullOrEmpty(dataSource)) {
        		datasourceName = "java:/xp";
        	} else {
        		datasourceName = "java:/"+dataSource;
        	}
        	if(Strings.isNullOrEmpty(hostDataSource)) {
        		hostDataSource = "SICATSGAP";
        	}
            Hashtable env = new Hashtable();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
            env.put(Context.PROVIDER_URL, hostDataSource);
            env.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
            ctx = new InitialContext(env);


        } catch (NamingException ex) {
        	Properties props = new Properties();
        	try {
        		props.load(getClass().getClassLoader().getResourceAsStream(databaseparameters));
        		datasourceName = props.getProperty("DATASOURCE_NAME");
        	} catch (IOException ex1) {
        		throw new RuntimeException(ex1 );
        	}        	
        }

    }


    public static ConnectionWrapper getInstance() {
        return me;
    }
    

    /* (non-Javadoc)
	 * @see br.com.compsisnet.j2eesmartlib.dao.IConnectionWrapper#getConnection()
	 */
	public Connection getConnection() throws RuntimeException {

        if (emTestes) {
            return getConnectionManual();
        } else {
            return getConnectionByPool();
        }
    }

    /**
     * Retorna o objeto de conexão por Pool
     *
     * @return Connection
     * @throws RuntimeException
     */


    private Connection getConnectionByPool() throws RuntimeException {

        Connection conn = null;
        try {

            DataSource ds = getDataSource();
            conn = ds.getConnection();
            driverClassName = conn.getMetaData().getDriverName();
            if (driverClassName.toUpperCase().indexOf("MYSQL") > -1) {
                dataBaseName = "MYSQL";
            } else if (driverClassName.toUpperCase().indexOf("MCKOI") > -1) {
                dataBaseName = "MCKOI";
            }

            return conn;
        } catch (SQLException se) {
        	throw new RuntimeException(se );
        }
    }

    /* (non-Javadoc)
	 * @see br.com.compsisnet.j2eesmartlib.dao.IConnectionWrapper#getDataBaseName()
	 */
	public String getDataBaseName() throws RuntimeException {
        if (dataBaseName == null) {
            Connection conn = getConnection();
            releaseResources(conn);
        }
        return dataBaseName;
    }

    /* (non-Javadoc)
	 * @see br.com.compsisnet.j2eesmartlib.dao.IConnectionWrapper#isEmTestes()
	 */
	public boolean isEmTestes() {
        return emTestes;
    }

    /**
     * Retorna o DataSouce. Atualmente so funciona com JBOSS
     * @throws RuntimeException
     * @return DataSource
     */


    private DataSource getDataSource() throws RuntimeException {

        try {
            DataSource ds = (DataSource) ctx.lookup(datasourceName);
            return ds;

        } catch (NamingException ex) {
        	throw new RuntimeException(ex );
        }
    }

    /* (non-Javadoc)
	 * @see br.com.compsisnet.j2eesmartlib.dao.IConnectionWrapper#releaseResources(java.sql.Connection, java.sql.PreparedStatement, java.sql.ResultSet)
	 */
	public void releaseResources( Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
        	throw new RuntimeException(e );
        }
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
        	throw new RuntimeException(e );
        }
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
        	throw new RuntimeException(e );
        }
    }

    /* (non-Javadoc)
	 * @see br.com.compsisnet.j2eesmartlib.dao.IConnectionWrapper#releaseResources(java.sql.Connection, java.sql.CallableStatement, java.sql.PreparedStatement, java.sql.ResultSet)
	 */
	public void releaseResources(Connection conn, CallableStatement cs, PreparedStatement ps, ResultSet rs){
    	try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
        	throw new RuntimeException(e );
        }
        
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
        	throw new RuntimeException(e );
        }
        
        try {
            if (cs != null) {
                cs.close();
            }
        }
        catch (SQLException e) {
        	throw new RuntimeException(e );
        }

        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
        	throw new RuntimeException(e );
        }        
    }

    /* (non-Javadoc)
	 * @see br.com.compsisnet.j2eesmartlib.dao.IConnectionWrapper#releaseResources(java.sql.Connection, java.sql.ResultSet)
	 */
	public void releaseResources(Connection conn, ResultSet rs){
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
        	throw new RuntimeException( e );
        }

        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
        	throw new RuntimeException(e );
        }


    }


    /* (non-Javadoc)
	 * @see br.com.compsisnet.j2eesmartlib.dao.IConnectionWrapper#releaseResources(java.sql.Connection)
	 */
	public void releaseResources( Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex );
        }
    }

    /**
     * Retorna uma conexão criada individualmente utilizando o driver JDBC
     * Este método deve ser utilizado apenas durante a fase de testes unitários
     * @return java.sql.Connection
     */
    private java.sql.Connection getConnectionManual() {
        java.sql.Connection conn = null;
        try {

            Properties props = new Properties();
            try {
                props.load(getClass().getClassLoader().getResourceAsStream(databaseparameters));
            } catch (IOException ex1) {
                throw new RuntimeException(ex1);
            }
            
            Class.forName(props.getProperty("JDBC_DRIVER"));
            conn =
                    DriverManager.getConnection(
                            props.getProperty("URL_CONEXAO"),
                            props.getProperty("USER"),
                            props.getProperty("SENHA"));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    /* (non-Javadoc)
	 * @see br.com.compsisnet.j2eesmartlib.dao.IConnectionWrapper#setEmTestes(boolean)
	 */
	public void setEmTestes(boolean emTestes) {
    	ConnectionWrapper.emTestes = emTestes;
    }
}
