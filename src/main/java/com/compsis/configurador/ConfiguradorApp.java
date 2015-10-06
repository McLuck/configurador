package com.compsis.configurador;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.compsis.configurador.telas.TelaInicial;



/**
 * Hello world!
 *
 */
public class ConfiguradorApp {
	
	public static void main(String[] args) {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("classpath*:*configurador.xml");
		TelaInicial telaInicial =  (TelaInicial) appContext.getBean("telaInicial");
		
		
	}
}
