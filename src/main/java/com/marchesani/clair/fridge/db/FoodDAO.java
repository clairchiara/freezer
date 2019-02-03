package com.marchesani.clair.fridge.db;

import com.google.common.collect.Lists;
import com.marchesani.clair.fridge.Food;
import com.marchesani.clair.fridge.FoodType;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Contains methods that operate on the DB to retrieve or save/update {@link Food}s
 */
public class FoodDAO extends BaseDAO {

    public FoodDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public void saveOrUpdate(Food food) {
        super.saveOrUpdate(food);
    }

    public Food getFoodById(Long id) {
        Criteria crit = getCriteriaForClass(Food.class, "food");
        crit.add(Restrictions.eq("id", id));
        return super.<Food>getTransactionally(crit::uniqueResult);
    }

    public List<Food> getFoodsByName(String name) {
        Criteria crit = getCriteriaForClass(Food.class, "food");
        crit.add(Restrictions.eq("name", name));
        return super.<List<Food>>getTransactionally(crit::list);
    }

    public List<Food> getFoodsByType(FoodType type) {
        Criteria crit = getCriteriaForClass(Food.class, "food");
        crit.add(Restrictions.eq("type", type));
        return super.<List<Food>>getTransactionally(crit::list);
    }

    public List<Food> getFoodsByDateAdded(Date date) {
        Criteria crit = getCriteriaForClass(Food.class, "food");
        crit.add(Restrictions.eq("dateAdded", date));
        return super.<List<Food>>getTransactionally(crit::list);
    }

}
