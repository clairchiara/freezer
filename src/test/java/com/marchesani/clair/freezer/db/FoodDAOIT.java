package com.marchesani.clair.freezer.db;

import static org.assertj.core.api.Assertions.*;

import com.google.common.collect.ImmutableList;
import com.marchesani.clair.freezer.Food;
import com.marchesani.clair.freezer.FoodType;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * Integration test for {@link FoodDAO}
 */
public class FoodDAOIT {

    private FoodDAO foodDAO;

    private H2MemoryDatabase db;

    private HibernateSupport hibernateSupport;

    @Before
    public void init() throws Exception {
        db = new H2MemoryDatabase();
        hibernateSupport = new HibernateSupport();
        SessionFactory sessionFactory = hibernateSupport.getSessionFactory();
        foodDAO = new FoodDAO(sessionFactory);
    }

    @After
    public void destroy() throws Exception {
        if (hibernateSupport != null) hibernateSupport.close();
    }

    private List<Food> saveSomeFoods() {
        Food apple = new Food();
        apple.setName("Onion");
        apple.setType(FoodType.VEGETABLES);
        apple.setAmount(5);
        apple.setDateAdded(new Date());
        foodDAO.saveOrUpdate(apple);

        Food beef = new Food();
        beef.setName("Beef mince");
        beef.setType(FoodType.MEAT);
        beef.setAmount(1);
        beef.setDateAdded(new Date());
        foodDAO.saveOrUpdate(beef);

        return ImmutableList.of(apple, beef);
    }

    @Test
    public void testSaveOrUpdateAndRetrieveByName() throws Exception {
        List<Food> foods = saveSomeFoods();
        foods.get(1).setAmount(5);
        foodDAO.saveOrUpdate(foods.get(1));
        Food retrievedFood = foodDAO.getFoodsByAttributes(FoodSearchParams.withName("Beef mince")).get(0);
        assertThat(retrievedFood)
                .extracting(Food::getName)
                .as("The correct food should be returned")
                .isEqualTo("Beef mince");
        assertThat(retrievedFood)
                .extracting(Food::getAmount)
                .as("The amount should've been updated")
                .isEqualTo(5);
        retrievedFood = foodDAO.getFoodsByAttributes(FoodSearchParams.withName("Onion")).get(0);
        assertThat(retrievedFood)
                .extracting(Food::getName)
                .as("The correct food should be returned")
                .isEqualTo("Onion");
    }

    @Test
    public void testRetrieveById() throws Exception {
        List<Food> foods = saveSomeFoods();
        for (Food food : foods) {
            Food retrievedFood = foodDAO.getFoodById(food.getId());
            assertThat(retrievedFood).as("The correct food should be retrieved").isEqualTo(food);
        }
    }

    @Test
    public void testRetrieveByType() throws Exception {
        List<Food> foods = saveSomeFoods();
        List<Food> foodList = foodDAO.getFoodsByAttributes(FoodSearchParams.withType(FoodType.VEGETABLES));
        assertThat(foodList)
                .as("One vegetable should be returned")
                .hasSize(1)
                .as("Vegetable should be the onion")
                .containsOnly(foods.get(0));
    }

    @Test
    public void testRetrieveByDateAdded() throws Exception {
        List<Food> foods = saveSomeFoods();
        List<Food> foodList = foodDAO.getFoodsByAttributes(FoodSearchParams.withDate(new Date()));
        assertThat(foodList)
                .as("Both foods should be returned as they were both added today")
                .containsOnly(foods.toArray(new Food[0]));
    }


}
