package com.example.internet.katalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.ArrayList;
import java.util.List;
@Controller
public class ProductController {

  @Autowired
  ProductService productService;

    public ProductController() {
        System.out.println("Создается контроллер");
//        System.out.println("productService = " + productService);
//        // Инициализация списка продуктов
//        productService.getProducts().add(new Product(1, "Микроволновка \"Победа\""));
//        productService.getProducts().add(new Product(2, "Холодильник \"Атлант\""));
//        productService.getProducts().add(new Product(3, "Стиральная машина \"Zanussi\""));
    }

    @GetMapping("/")
    public String getAllProducts(Model model) {
        model.addAttribute("products", productService.getProducts());
        return "index";  // название HTML-шаблона
    }

    @GetMapping("/product/{id}")
    public String getProductById(@PathVariable int id, Model model) {
        Product product = productService.getProductById(id);
        if (product != null) {
            model.addAttribute("product", product);
            return "product";  // название HTML-шаблона для деталей товара
        }
        return "redirect:/";  // если не найден, вернуться на главную
    }

    @GetMapping("/prodInfo")
    public String getOneProduct( String id, Model model) {
        try {
            int i = Integer.parseInt(id);
            Product product = productService.getProductById(i);
            if (product != null) {
                model.addAttribute("product", product);
                return "product";  // название HTML-шаблона для деталей товара
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/";  // если не найден, вернуться на главную
    }
    @GetMapping("/deleteProd")
    public String deleteProduct( String id, Model model) {
        try {
            int i = Integer.parseInt(id);
            productService.removeById(i);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/";  // если не найден, вернуться на главную
    }
}
