package com.compsis.configurador;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



/**
 * Hello world!
 *
 */
public class ConfiguradorApp {
	
	public static void main(String[] args) {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("classpath*:*configurador.xml");
		Object beanTeste = appContext.getBean("beanTeste");
		System.out.println(beanTeste);
	}
}