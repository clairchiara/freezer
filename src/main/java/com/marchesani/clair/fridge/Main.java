package com.marchesani.clair.fridge;

import com.marchesani.clair.fridge.db.FoodDAO;
import com.marchesani.clair.fridge.db.H2MemoryDatabase;

public class Main {

    public static void main(String[] args) throws Exception {
        H2MemoryDatabase db = new H2MemoryDatabase(FoodDAO.CREATE_FOOD_TABLE);
    }

}
