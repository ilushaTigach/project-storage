package org.telyatenko.storage.service.domain.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telyatenko.storage.service.api.exception.NotFoundProductException;
import org.telyatenko.storage.service.domain.models.Product;
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
        if (product.getStorage().getSizeNow() >= product.getStorage().getSize()) {
            throw new RuntimeException("The storage is full");
        }else {
            return product1;
        }
    }

    public void deleteProduct(UUID id) {
        productRepository.findById(id).orElseThrow(() -> new NotFoundProductException("id", id.toString()));
        storageService.updateStorageSizeNow(getById(id).getStorage().getId(),
            (getById(id).getStorage().getSizeNow() - getById(id).getSize()));
        productRepository.deleteById(id);
    }


    public Product getById(UUID id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundProductException("id", id.toString()));
    }

    public Product updateProduct(UUID id, String title, String author, String description, int size) {
        productRepository.updateProduct(id, title, author, description, size);
        return productRepository.findById(id).orElseThrow();
    }

}
