package org.telyatenko.storage.service.domain.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telyatenko.storage.service.api.dto.ProductDto;
import org.telyatenko.storage.service.domain.mappers.ProductMapper;
import org.telyatenko.storage.service.domain.models.Product;
import org.telyatenko.storage.service.domain.services.ProductService;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {


    @Mock
    private ProductService productService;

    @Mock
    private ProductMapper productMapper;
    @InjectMocks
    private ProductController productController;

    @Test
    void products_shouldReturnProductsDto() {

        Product testProduct1 = new Product();
        testProduct1.setId(UUID.randomUUID());
        Product testProduct2 = new Product();
        testProduct2.setId(UUID.randomUUID());

        ProductDto testProductDto1 = new ProductDto();
        testProductDto1.setId(testProduct1.getId());
        ProductDto testProductDto2 = new ProductDto();
        testProductDto2.setId(testProduct2.getId());

        List<Product> productList = Arrays.asList(testProduct1, testProduct2);
        List<ProductDto> productDtoList = Arrays.asList(testProductDto1, testProductDto2);

        when(productService.listProducts()).thenReturn(productList);
        when(productMapper.toDto(productList)).thenReturn(productDtoList);

        List<ProductDto> result = productController.products(new ProductDto());

        assertEquals(2, result.size());
        assertEquals(productDtoList, result);
    }

    @Test
    void getProductById_shouldReturnProductDto() {
        UUID id = UUID.randomUUID();
        Product productEntity = new Product();
        productEntity.setId(id);

        ProductDto productDto = new ProductDto();
        productDto.setId(id);

        when(productService.getById(id)).thenReturn(productEntity);
        when(productMapper.toDto(productEntity)).thenReturn(productDto);

        ProductDto result = productController.getProductById(id);

        assertEquals(productDto, result);
    }

    @Test
    void createProduct_shouldCreateProduct() {
        UUID id = UUID.randomUUID();
        Product productEntity = new Product();
        productEntity.setId(id);

        ProductDto productDto = new ProductDto();
        productDto.setId(id);

        when(productMapper.toEntity(productDto)).thenReturn(productEntity);
        when(productService.saveProduct(productEntity)).thenReturn(productEntity);
        when(productMapper.toDto(productEntity)).thenReturn(productDto);

        ProductDto result = productController.createProduct(productDto);

        assertEquals(productDto, result);
    }
}