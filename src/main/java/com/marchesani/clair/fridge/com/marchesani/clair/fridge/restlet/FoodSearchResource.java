package com.marchesani.clair.fridge.com.marchesani.clair.fridge.restlet;

import com.marchesani.clair.fridge.Food;
import com.marchesani.clair.fridge.db.FoodSearchParams;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;

import java.util.List;

public class FoodSearchResource extends AbstractFoodResource {

    @Get("json")
    public List<Food> get(FoodSearchParams searchParams) {
        return foodDAO.getFoodsByAttributes(searchParams);
    }

}
