package com.marchesani.clair.fridge;

import com.marchesani.clair.fridge.db.DBEntity;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Represents a food in the fridge.
 */
@Entity
@Table(name = "FOOD", uniqueConstraints = {@UniqueConstraint(columnNames = "ID")})
public  class Food implements DBEntity {

    private Long id;
    private String name;
    private FoodType type;
    private Date dateAdded = new Date();
    private Integer amount;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Id
    @GeneratedValue
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

    @Enumerated(value = EnumType.STRING)
    private FoodType getType() {
        return type;
    }

    public void setType(FoodType type) {
        this.type = type;
    }

    @Type(type = "date")
    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        if (!(o instanceof Food)) return false;
        Food other = (Food) o;
        return Objects.equals(id, other.id)
                && Objects.equals(name, other.name)
                && Objects.equals(type, other.type)
                && DateUtils.isSameDay(dateAdded, other.dateAdded)
                && Objects.equals(amount, other.amount);
    }

    @Override
    public int hashCode() {
        return com.google.common.base.Objects.hashCode(id, name, type, dateAdded, amount);
    }

    @Override
    public String toString() {
        return new StringJoiner("|").setEmptyValue("null")
                .add(Objects.toString(id))
                .add(name)
                .add(Objects.toString(name))
                .add(Objects.toString(dateAdded))
                .add(Objects.toString(amount))
                .toString();
    }

}
