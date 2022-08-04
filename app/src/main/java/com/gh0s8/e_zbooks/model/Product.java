package com.gh0s8.e_zbooks.model;

import java.util.ArrayList;

public class Product {

    private String id,name,imgPath,description,category;
    private double price;
    private ArrayList<String> sizes = new ArrayList<>();

    public Product() {
    }

    public Product(String id, String name, String imgPath,String description, String category, double price) {
        this.id = id;
        this.name = name;
        this.imgPath = imgPath;
        this.description = description;
        this.category = category;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImagePath() {
        return imgPath = imgPath;
    }

    public ArrayList<String> getSizes() { return sizes;}

    public void setSizes(ArrayList<String> sizes) {
        this.sizes = sizes;
 }
}
