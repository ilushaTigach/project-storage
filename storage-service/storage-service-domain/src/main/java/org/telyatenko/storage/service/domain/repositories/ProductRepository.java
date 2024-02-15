package org.telyatenko.storage.service.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.telyatenko.storage.service.domain.models.Product;
import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findByTitle(String title);

    @Modifying
    @Transactional
    @Query(value = """ 
            UPDATE products
            SET title = COALESCE(NULLIF(?2, ''), title),
                author = COALESCE(NULLIF(?3, ''), author),
                description = COALESCE(NULLIF(?4, ''), description)
                WHERE id = ?1""", nativeQuery = true)
    void updateProduct(UUID id, String title, String author, String description);
}
