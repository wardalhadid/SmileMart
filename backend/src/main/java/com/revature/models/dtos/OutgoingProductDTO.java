package com.revature.models.dtos;

import com.revature.models.Category;

public class OutgoingProductDTO {

    private String name;

    private double cost;

    private String description;

    private Category category;

    private String image;

    public OutgoingProductDTO() {
    }

    public OutgoingProductDTO(String name, double cost, String description, Category category, String image) {
        this.name = name;
        this.cost = cost;
        this.description = description;
        this.category = category;
        this.image = image;
    }


    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
