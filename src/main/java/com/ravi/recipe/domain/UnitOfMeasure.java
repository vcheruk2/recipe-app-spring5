package com.ravi.recipe.domain;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 3/28/2020 */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UnitOfMeasure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String unitOfMeasure) {
        this.description = unitOfMeasure;
    }
}
