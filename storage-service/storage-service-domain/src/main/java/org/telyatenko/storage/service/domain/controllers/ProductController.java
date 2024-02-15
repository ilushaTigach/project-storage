package org.telyatenko.storage.service.domain.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.telyatenko.storage.service.api.dto.ProductDto;
import org.telyatenko.storage.service.api.resource.ProductResource;
import org.telyatenko.storage.service.domain.mappers.ProductMapper;
import org.telyatenko.storage.service.domain.models.Product;
import org.telyatenko.storage.service.domain.services.ProductService;
import java.util.List;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
public class ProductController implements ProductResource {

    private final ProductService productService;
    private final ProductMapper productMapper;

    public List<ProductDto> products(ProductDto productDto) {
        List<Product> product = productService.listProducts();
        return productMapper.toDto(product);
    }

    public ProductDto getProductById(@PathVariable("id") UUID id) {
        Product product = productService.getById(id);
        return productMapper.toDto(product);
    }

    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        Product product = productMapper.toEntity(productDto);
        return productMapper.toDto(productService.saveProduct(product));
    }

    public void deleteProduct(@PathVariable("id") UUID id) {
        productService.deleteProduct(id);
    }

    public ProductDto updateProduct(@PathVariable("id") UUID id, @RequestBody ProductDto productDto) {
        Product product = productService.updateProduct(id, productDto.getTitle(), productDto.getAuthor(),
                productDto.getDescription());
        return productMapper.toDto(product);
    }
}

