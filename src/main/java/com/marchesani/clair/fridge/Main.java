package com.marchesani.clair.fridge;

import com.marchesani.clair.fridge.db.FoodDAO;
import com.marchesani.clair.fridge.db.H2MemoryDatabase;
import com.marchesani.clair.fridge.db.HibernateSupport;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

public class Main {

    private static HibernateSupport hibernateSupport = new HibernateSupport();

    public static void main(String[] args) throws Exception {
        H2MemoryDatabase db = new H2MemoryDatabase();
        SessionFactory sessionFactory = hibernateSupport.getSessionFactory();
        FoodDAO foodDAO = new FoodDAO(sessionFactory);
    }

}
