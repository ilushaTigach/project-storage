package org.telyatenko.storage.service.domain.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telyatenko.storage.service.domain.models.Product;
import org.telyatenko.storage.service.domain.repositories.ProductRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

//    private long ID = 0;
//    private List<Product> products = new ArrayList<>();
//
//    {
//        products.add(new Product(++ID, "PlayStation 5", "Simpe description", 67000, "Stepan"));
//        products.add(new Product(++ID, "Xbox", "Simpe description", 51000, "Ilya"));
//    }

    private final ProductRepository productRepository;
    public List<Product> listProducts(String title) {
        if (title != null) return productRepository.findByTitle(title);
        return productRepository.findAll();
    }

    public void saveProduct(Product product) {
        log.info("Saving new {}", product);
        productRepository.save(product);
        //параметр в каком складе я его храню
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
