package com.example.melichallengebuscadormatiasdominguez.Objects;

public class Product {

    private String name;
    private int price;
    private String currency;

    private int quantity;
    private String condition;
    private String image;

    public Product(String name, int price, String currency, int quantity, String condition, String image) {
        this.name = name;
        this.price = price;
        this.currency = currency;
        this.quantity = quantity;
        this.condition = condition;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
