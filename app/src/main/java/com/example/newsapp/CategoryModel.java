package com.example.newsapp;

public class CategoryModel {
    private String category_name;
    private String imgSrc;

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public CategoryModel(String category_name, String imgSrc) {
        this.category_name = category_name;
        this.imgSrc = imgSrc;
    }
}
