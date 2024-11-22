package com.example.internet.katalog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.ArrayList;
import java.util.List;
@Controller
public class ProductController {

    private List<Product> products = new ArrayList<>();

    public ProductController() {
        // Инициализация списка продуктов
        products.add(new Product(1, "Микроволновка \"Победа\""));
        products.add(new Product(2, "Холодильник \"Атлант\""));
        products.add(new Product(3, "Стиральная машина \"Zanussi\""));
    }

    @GetMapping("/")
    public String getAllProducts(Model model) {
        model.addAttribute("products", products);
        return "index";  // название HTML-шаблона
    }

    @GetMapping("/product/{id}")
    public String getProductById(@PathVariable int id, Model model) {
        for (Product product : products) {
            if (product.getId() == id) {
                model.addAttribute("product", product);
                return "product";  // название HTML-шаблона для деталей товара
            }
        }
        return "redirect:/";  // если не найден, вернуться на главную
    }
}
