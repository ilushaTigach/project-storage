package org.telyatenko.storage.service.domain.services;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.telyatenko.storage.service.api.exception.ProductResponseError;
import org.telyatenko.storage.service.api.exception.RequiredException;
import org.telyatenko.storage.service.domain.models.Product;
import org.telyatenko.storage.service.domain.models.Storage;
import org.telyatenko.storage.service.domain.repositories.ProductRepository;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final StorageService storageService;

    public List<Product> listProducts() {
        return productRepository.findAll();
    }

    public Product saveProduct(Product product) {
        log.info("Saving new {}", product);
        Product product1 = productRepository.save(product);
        storageService.updateStorageSizeNow(product.getStorage().getId(),
                (product.getStorage().getSizeNow() + product.getSize()));
        return product1;
    }

    public void deleteProduct(UUID id) {
        storageService.updateStorageSizeNow(getById(id).getStorage().getId(),
            (getById(id).getStorage().getSizeNow() - getById(id).getSize()));
        productRepository.deleteById(id);
    }


    public Product getById(UUID id) {
        return productRepository.findById(id).orElseThrow(() -> new RequiredException(id.toString()));
    }

    public Product updateProduct(UUID id, String title, String author, String description, int size) {
        productRepository.updateProduct(id, title, author, description, size);
        return productRepository.findById(id).orElseThrow();
    }
}
