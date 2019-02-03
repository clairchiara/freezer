package com.marchesani.clair.fridge.db;

import org.junit.Test;

/**
 * Integration test for H2 in memory Database
 */
public class H2MemoryDatabaseIT {

    @Test
    public void testH2MemoryDatabase() throws Exception {
        // Initialises DB with 2 queries
        H2MemoryDatabase db = new H2MemoryDatabase("CREATE TABLE FOOD(ID INT AUTO_INCREMENT PRIMARY KEY, " +
                "NAME VARCHAR(255), " +
                "TYPE VARCHAR(255), " +
                "DATE_ADDED TIMESTAMP, " +
                "QUANTITY INT)",
                "INSERT INTO FOOD (NAME, TYPE, QUANTITY) VALUES ('APPLE', 'FRESH', 5)");
    }

}
