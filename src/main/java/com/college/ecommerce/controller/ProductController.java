package com.college.ecommerce.controller;

import com.college.ecommerce.model.Product;
import com.college.ecommerce.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;

@Controller
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("products", service.getAllProducts());
        return "index";
    }

    @GetMapping("/add")
    public String addForm() {
        return "add-product";
    }

    @PostMapping("/save")
    public String saveProduct(@RequestParam String name,
                              @RequestParam String description,
                              @RequestParam double price,
                              @RequestParam MultipartFile image)
            throws IOException {

        String uploadDir = "src/main/resources/static/images/";
        String fileName = image.getOriginalFilename();
        Path path = Paths.get(uploadDir + fileName);
        Files.write(path, image.getBytes());

        Product p = new Product();
        p.setName(name);
        p.setDescription(description);
        p.setPrice(price);
        p.setImagePath("/images/" + fileName);

        service.saveProduct(p);

        return "redirect:/";
    }
}