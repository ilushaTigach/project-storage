package org.telyatenko.storage.service.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.telyatenko.storage.service.domain.models.Product;
import org.telyatenko.storage.service.domain.models.Storage;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByTitle(String title);

}
