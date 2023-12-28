package org.telyatenko.storage.service.domain.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.telyatenko.storage.service.domain.models.Product;
import org.telyatenko.storage.service.domain.services.ProductService;

import java.util.List;


@RestController
@RequiredArgsConstructor

public class ProductController {

    private final ProductService productService;

    @GetMapping("/product")
    public List<Product> products(@RequestParam(name = "title", required = false) String title) {
        return productService.listProducts(title);
    }

    @PostMapping("/product/create")
    public void createProduct(@RequestBody Product product) {
        productService.saveProduct(product);
    }

    @DeleteMapping("/product/{id}/delete")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}

