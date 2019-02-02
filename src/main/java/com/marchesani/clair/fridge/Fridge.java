package com.marchesani.clair.fridge;

import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the fridge which contains various {@link Food}s
 */
public class Fridge {

    private final Map<Food, Integer> contents = Maps.newHashMap();

    /**
     * Adds a new food to the fridge
     * @param food: food to be added
     * @param amount: how many to add
     * @return: {@literal true} if the food already existed and only its quantity has been increased.
     * False if a new food was added
     */
    public boolean addFood(Food food, Integer amount) {
        boolean alreadyInFridge = contents.containsKey(food);
        if (alreadyInFridge) {
            Integer previousAmount = contents.get(food);
            contents.remove(food);
            contents.put(food, previousAmount + amount);
        } else {
            contents.put(food, amount);
        }
        return alreadyInFridge;
    }

}
