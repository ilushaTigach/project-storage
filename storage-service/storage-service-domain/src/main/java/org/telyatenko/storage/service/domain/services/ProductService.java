package org.telyatenko.storage.service.domain.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telyatenko.storage.service.domain.models.Product;
import org.telyatenko.storage.service.domain.repositories.ProductRepository;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> listProducts() {
        return productRepository.findAll();
    }

    public Product saveProduct(Product product) {
        log.info("Saving new {}", product);
        return productRepository.save(product);
    }

    public void deleteProduct(UUID id) {
        productRepository.deleteById(id);
    }

    public Product getById(UUID id) {
        return productRepository.findById(id).orElseThrow();
    }

    public Product updateProduct(UUID id, String title, String author, String description) {
        productRepository.updateProduct(id, title, author, description);
        return productRepository.findById(id).orElseThrow();
    }
}
