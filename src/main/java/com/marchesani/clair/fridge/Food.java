package com.marchesani.clair.fridge;

import com.google.common.base.Objects;
import org.apache.commons.lang3.ObjectUtils;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;

/**
 * Represents a food in the fridge.
 */
public  class Food {

    private Long id;
    private String name;
    private FoodType type;
    private Date dateAdded;
    private Integer amount;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
