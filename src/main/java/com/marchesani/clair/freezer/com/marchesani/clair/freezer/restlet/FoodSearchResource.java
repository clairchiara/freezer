package com.marchesani.clair.freezer.com.marchesani.clair.freezer.restlet;

import com.marchesani.clair.freezer.Food;
import com.marchesani.clair.freezer.db.FoodSearchParams;
import org.restlet.resource.Get;

import java.util.List;

public class FoodSearchResource extends AbstractFoodResource {

    @Get("json")
    public List<Food> get(FoodSearchParams searchParams) {
        return foodDAO.getFoodsByAttributes(searchParams);
    }

}
