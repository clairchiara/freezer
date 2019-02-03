package com.marchesani.clair.fridge.db;

import com.google.common.collect.Lists;
import com.marchesani.clair.fridge.Food;
import com.marchesani.clair.fridge.FoodType;
import org.hibernate.SessionFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class FoodDAO {

    private SessionFactory sessionFactory;

    public static final String CREATE_FOOD_TABLE = "CREATE TABLE FOOD(ID INT AUTO_INCREMENT PRIMARY KEY, " +
            "NAME VARCHAR(255), " +
            "TYPE VARCHAR(255), " +
            "DATE_ADDED TIMESTAMP, " +
            "QUANTITY INT)";


    public FoodDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }



}
