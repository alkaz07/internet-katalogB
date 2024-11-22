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

    public ProductService() {
        System.out.println("создается ProductService");
        products.add(new Product(1, "Микроволновка \"Победа\""));
        products.add(new Product(2, "Холодильник \"Атлант\""));
        products.add(new Product(3, "Стиральная машина \"Zanussi\""));
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
}
