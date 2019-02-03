package com.marchesani.clair.fridge.com.marchesani.clair.fridge.restlet;

import com.marchesani.clair.fridge.Food;
import com.marchesani.clair.fridge.db.FoodDAO;
import org.restlet.Application;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

public class FoodResource extends AbstractFoodResource {

    @Post("json")
    public Long accept(Food entity) {
        Application app =  getApplication();
        foodDAO.saveOrUpdate(entity);
        return entity.getId();
    }

    @Get("json")
    public Food fromId(Long id) {
        return foodDAO.getFoodById(id);
    }

}
