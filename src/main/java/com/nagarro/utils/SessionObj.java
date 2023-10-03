package com.nagarro.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class SessionObj {
	
	private static Configuration cfg=null;
	private static ServiceRegistry sr=null;
	private static SessionFactory sf=null; 
	private static Session session=null;
	public static Session getSession() {
		if(session==null) {
			cfg=new Configuration().configure();
			sf=cfg.buildSessionFactory();
			return sf.openSession();
		}
		
		return session;
		
	}
}
