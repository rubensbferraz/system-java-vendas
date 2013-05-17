/*Aqui devolve a sessão pro usuário*/

package br.com.fernanda.vendas;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
//import org.hibernate.cfg.Configuration;


public class HibernateUtil {
	
	private static final SessionFactory session = buildSessionFactory();
	
	private static SessionFactory buildSessionFactory() {
		try {/*
			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			return cfg.buildSessionFactory(); //retorna a sessão*/
			AnnotationConfiguration cfg = new AnnotationConfiguration();
			cfg.configure("hibernate.cfg.xml");
			return cfg.buildSessionFactory();
			
			//return new AnnotationConfiguration().configure().buildSessionFactory();
		} catch (Throwable e) {
			System.out.println("Ocorreu algum problema!!!\n" + e);
			throw new ExceptionInInitializerError();
		}
		
	}

	public static SessionFactory getSessionFactory() {
		return session;
	}
	
}
