package com.marchesani.clair.freezer.com.marchesani.clair.freezer.restlet;

import com.marchesani.clair.freezer.Food;
import org.restlet.Application;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

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
