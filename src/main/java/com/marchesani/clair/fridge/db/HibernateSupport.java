package com.marchesani.clair.fridge.db;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateSupport {

    private static final String RESOURCE_PATH = "/hibernate.cfg.xml";

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration config = new Configuration();
            config.configure(HibernateSupport.class.getResource(RESOURCE_PATH));
            StandardServiceRegistryBuilder serviceRegBuilder = new StandardServiceRegistryBuilder();
            serviceRegBuilder.applySettings(config.getProperties());
            ServiceRegistry serviceRegistry = serviceRegBuilder.build();
            sessionFactory = config.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }

}
