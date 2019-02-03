package com.marchesani.clair.freezer.db;

import com.marchesani.clair.freezer.Food;
import com.marchesani.clair.freezer.FoodType;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import java.util.Date;

/**
 * Contains parameters to be used for DB queries of {@link Food}s
 */
public class FoodSearchParams {

    private String name;

    private FoodType type;

    private Date dateAdded;

    public FoodSearchParams() { /* For JSON parser */ }

    public FoodSearchParams(String name, FoodType type, Date dateAdded) {
        this.name = name;
        this.type = type;
        this.dateAdded = dateAdded;
    }

    public static FoodSearchParams withName(String name) {
        return new FoodSearchParams(name, null, null);
    }

    public static FoodSearchParams withType(FoodType type) {
        return new FoodSearchParams(null, type, null);
    }

    public static FoodSearchParams withDate(Date date) {
        return new FoodSearchParams(null, null, date);
    }

    public Criterion generateRestrictions() {
        Conjunction conj = new Conjunction();
        if (name != null) conj.add(Restrictions.eq("name", name));
        if (type != null) conj.add(Restrictions.eq("type", type));
        if (dateAdded != null) conj.add(Restrictions.eq("dateAdded", dateAdded));
        return conj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FoodType getType() {
        return type;
    }

    public void setType(FoodType type) {
        this.type = type;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }
}
