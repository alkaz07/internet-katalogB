package com.example.internet.katalog;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductService {
    private List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return products;
    }
    public List<Product> getProductsWithImg() {
        return products.stream()
                .filter(product -> product.getImg()!=null && !product.getImg().isEmpty())
                .toList();
    }
    public List<Product> getProductsWithOutImg() {
        return products.stream()
                .filter(product -> product.getImg()==null || product.getImg().isEmpty())
                .toList();
    }

    public List<Product> getProductsOfType(String type) {
        return products.stream()
                .filter(product -> product.getType().toLowerCase().equals(type.toLowerCase()))
                .toList();
    }

    public ProductService() {
        System.out.println("создается ProductService");
        products.add(new Product(1, "Микроволновка \"Победа\"",null, "бытовая техника"));
        products.add(new Product(2, "Холодильник \"Атлант\"", "/images/1694731089_balthazar-club-p-skulptura-derzhashchaya-potolok-instagram-10.jpg","бытовая техника"));
        products.add(new Product(3, "Стиральная машина \"Zanussi\"","","бытовая техника"));
        products.add(new Product(4, "Раб лампы", "/images/WIN_20241115_17_47_33_Pro.jpg" ,"рабочая сила"));
        products.add(new Product(5, "Кастрюля", "","посуда"));
    }

    public Product getProductById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;  // если не найден, вернуться на главную
    }

    public void removeById(int id){
        Product p = getProductById(id);
        if(p!=null)
            products.remove(p);
    }

    public List<String> getTypes(){
        return products.stream().map(Product::getType).distinct().sorted().toList();
    }
}
