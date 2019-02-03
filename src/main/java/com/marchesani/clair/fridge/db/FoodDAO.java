package com.marchesani.clair.fridge.db;

import com.marchesani.clair.fridge.Food;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

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

    public List<Food> getFoodsByAttributes(FoodSearchParams searchCrit) {
        Criteria crit = getCriteriaForClass(Food.class, "food");
        crit.add(searchCrit.generateRestrictions());
        return super.<List<Food>>getTransactionally(crit::list);
    }

}
