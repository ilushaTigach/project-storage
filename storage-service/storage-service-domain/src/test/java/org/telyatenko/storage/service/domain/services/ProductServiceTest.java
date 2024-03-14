package org.telyatenko.storage.service.domain.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telyatenko.storage.service.api.exception.NotFoundProductException;
import org.telyatenko.storage.service.domain.models.Product;
import org.telyatenko.storage.service.domain.models.Storage;
import org.telyatenko.storage.service.domain.repositories.ProductRepository;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @Mock
    StorageService storageService;

    @InjectMocks
    ProductService productService;

    @Test
    public void testSaveProduct() {
        UUID id = UUID.randomUUID();

        Product product = new Product();
        product.setId(id);
        product.setTitle("Test Product");
        product.setSize(10);

        Storage storage = new Storage();
        storage.setId(id);
        storage.setSize(100);
        storage.setSizeNow(50);

        product.setStorage(storage);

        when(productRepository.save(product)).thenReturn(product);

        Product savedProduct = productService.saveProduct(product);

        verify(storageService).updateStorageSizeNow(product.getStorage().getId(),
                (product.getStorage().getSizeNow()) + product.getSize());

        assertEquals("Test Product", savedProduct.getTitle());
    }

    @Test
    public void testSaveProduct_StorageFull() {
        UUID id = UUID.randomUUID();

        Product product = new Product();
        product.setId(id);
        product.setTitle("Test Product");
        product.setSize(60);

        Storage storage = new Storage();
        storage.setId(id);
        storage.setSize(100);
        storage.setSizeNow(50 + product.getSize());

        product.setStorage(storage);

        assertThrows(RuntimeException.class, () -> productService.saveProduct(product));
    }

    @Test
    public void testDeleteProduct() {
        UUID id = UUID.randomUUID();
        Product product = new Product();
        product.setId(id);
        product.setSize(10);

        Storage storage = new Storage();
        storage.setId(UUID.randomUUID());
        storage.setSizeNow(10);

        product.setStorage(storage);

        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        productService.deleteProduct(id);

        verify(storageService).updateStorageSizeNow(storage.getId(), storage.getSizeNow() - product.getSize());
        verify(productRepository).deleteById(id);
    }

    @Test
    public void testDeleteProductNotFound() {
        UUID id = UUID.randomUUID();

        when(productRepository.findById(id)).thenReturn(Optional.empty());

        NotFoundProductException exception = assertThrows(NotFoundProductException.class, () -> productService.deleteProduct(id));

        verify(productRepository).findById(id);
        verify(storageService, Mockito.never()).updateStorageSizeNow(Mockito.any(), Mockito.any());
        verify(productRepository, Mockito.never()).deleteById(Mockito.any());

        assertEquals("Product with 'id' = " + id + " not found", exception.getMessage());
    }

    @Test
    public void testGetByIdNotFound() {
        UUID id = UUID.randomUUID();

        when(productRepository.findById(id)).thenReturn(Optional.empty());

        NotFoundProductException exception = assertThrows(NotFoundProductException.class, () -> productService.getById(id));

        assertEquals("Product with 'id' = " + id + " not found", exception.getMessage());
    }

    @Test
    public void testUpdateProduct() {
        UUID id = UUID.randomUUID();
        String title = "New Title";
        String author = "New Author";
        String description = "New Description";
        int size = 10;

        Product product = new Product();
        product.setId(id);
        product.setTitle(title);
        product.setAuthor(author);
        product.setDescription(description);
        product.setSize(size);

        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        Product updatedProduct = productService.updateProduct(id, title, author, description, size);

        verify(productRepository).updateProduct(id, title, author, description, size);
        verify(productRepository).findById(id);
        assertEquals(title, updatedProduct.getTitle());
        assertEquals(author, updatedProduct.getAuthor());
        assertEquals(description, updatedProduct.getDescription());
        assertEquals(size, updatedProduct.getSize());
    }
}