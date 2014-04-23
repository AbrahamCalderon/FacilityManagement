package com.facilitymanagement.dal;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateMySQLHelper {
	
	private static final SessionFactory sessionFactory;
	
	static {
		
		try {
		
			Configuration configuration = new Configuration().configure();
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			
			// Create the SessionFactory from hibernate.cfg.xml
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			System.out.println("*************** Session Factory instantiated ..................");
			
		} catch (Throwable ex) {
			
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
