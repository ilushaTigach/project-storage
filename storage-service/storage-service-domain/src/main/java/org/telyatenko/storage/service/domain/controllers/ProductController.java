package org.telyatenko.storage.service.domain.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.telyatenko.storage.service.domain.dto.ProductDto;
import org.telyatenko.storage.service.domain.models.Product;
import org.telyatenko.storage.service.domain.services.ProductService;
import org.telyatenko.storage.service.domain.services.StorageService;
import java.util.List;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final StorageService storageService;

    @GetMapping("/api/v1/products") //Дополнителеньный маппер если вдруг захочется посмотреть все продукты
    public List<Product> products(@RequestParam(name = "title", required = false) String title) {
        return productService.listProducts(title);
    }

    @GetMapping("/api/v1/product/{id}")
    public Product getProductById(@PathVariable("id") UUID id) {
        return productService.getById(id);
    }

    @PostMapping("/api/v1/product")
    public void createProduct(@RequestBody ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setAuthor(productDto.getAuthor());
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setStorage(storageService.getById(productDto.getStorageId()));
        productService.saveProduct(product);
    }

    @DeleteMapping("/api/v1/product/{id}")
    public void deleteProduct(@PathVariable("id") UUID id) {
        productService.deleteProduct(id);
    }

    @PatchMapping("/api/v1/product/{id}")
    public Product updateProduct(@PathVariable("id") UUID id, @RequestBody Product product) {
        return productService.updateProduct(id, product.getTitle(), product.getAuthor(), product.getDescription());
    }
}

