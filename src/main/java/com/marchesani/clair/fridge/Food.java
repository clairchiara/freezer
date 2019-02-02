package com.marchesani.clair.fridge;

import com.google.common.base.Objects;
import org.apache.commons.lang3.ObjectUtils;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;

/**
 * Represents a food in the fridge
 */
public final class Food {

    private final Long id;
    private final String name;
    private final FoodType type;
    private final Date dateAdded;
    private final int hashcode;
    private static final Random RANDOM = new SecureRandom();

    public Food(String name, FoodType type) {
        this.id = RANDOM.nextLong();
        this.name = name;
        this.type = type;
        this.dateAdded = new Date();
        this.hashcode = Objects.hashCode(id, name, type, dateAdded);
    }

    @Override
    public int hashCode() {
        return hashcode;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        if (!(o instanceof Food)) return false;
        Food other = (Food) o;
        return Objects.equal(id, other.id)
                && Objects.equal(name, other.name)
                && Objects.equal(type, other.type)
                && Objects.equal(dateAdded, other.dateAdded);
    }

    /**
     * Returns true if two {@link Food}s have the same name and type
     * @param lhs: the first food
     * @param rhs: the other food
     * @return: whether the two {@link Food}s are the same in name and type
     */
    public static boolean areSame(Food lhs, Food rhs) {
        return Objects.equal(lhs.name, rhs.name) && Objects.equal(lhs.type, rhs.type);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public FoodType getType() {
        return type;
    }

}
