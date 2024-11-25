package com.example.internet.katalog;

public class Product {
    private int id;
    private String name;
    private String img;
    private String type;

    public Product(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Product(int id, String name, String img) {
        this.id = id;
        this.name = name;
        this.img = img;
    }

    public Product(int id, String name, String img, String type) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getImg() {
        return img;
    }

    public int getId() {
        return id;
    }

    public String getName() {

        return name;
    }
}

