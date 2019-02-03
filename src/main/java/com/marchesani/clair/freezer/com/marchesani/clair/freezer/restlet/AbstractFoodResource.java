package com.marchesani.clair.freezer.com.marchesani.clair.freezer.restlet;

import com.marchesani.clair.freezer.db.FoodDAO;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

public abstract class AbstractFoodResource extends ServerResource {

    public static final String FOOD_DAO_KEY = "FOOD_DAO";

    protected FoodDAO foodDAO;

    protected void doInit() throws ResourceException {
        foodDAO = (FoodDAO) getContext().getAttributes().get(FOOD_DAO_KEY);
    }

}
