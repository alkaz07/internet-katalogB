package com.example.internet.katalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


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
        model.addAttribute("types", productService.getTypes());
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
    public String getOneProduct(String id, Model model) {
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
        model.addAttribute("types", productService.getTypes());
        return "redirect:/";  // если не найден, вернуться на главную
    }

    @GetMapping("/deleteProd")
    public String deleteProduct(String id, Model model) {
        try {
            int i = Integer.parseInt(id);
            productService.removeById(i);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        model.addAttribute("types", productService.getTypes());
        return "redirect:/";  // если не найден, вернуться на главную
    }

    @GetMapping("/filter")
    public String getFilteredProducts(@RequestParam(name = "withImg", defaultValue = "off") String withImg,
                                      @RequestParam(name = "withOutImg", defaultValue = "off") String withOutImg,
                                      Model model) {
        if (withImg.equals("on") && withOutImg.equals("on"))
            model.addAttribute("products", productService.getProducts());
        if (withImg.equals("off") && withOutImg.equals("on"))
            model.addAttribute("products", productService.getProductsWithOutImg());
        if (withImg.equals("on") && withOutImg.equals("off"))
            model.addAttribute("products", productService.getProductsWithImg());
        model.addAttribute("types", productService.getTypes());
        return "index";  // название HTML-шаблона
    }

    @GetMapping("/type/{type}")
    public String getTypeProducts(@PathVariable String type,
                                      Model model) {
        System.out.println("type = " + type);
        model.addAttribute("products", productService.getProductsOfType(type));
        model.addAttribute("types", productService.getTypes());
        return "index";  // название HTML-шаблона
    }
}
