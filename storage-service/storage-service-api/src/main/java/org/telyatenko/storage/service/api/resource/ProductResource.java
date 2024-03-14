package org.telyatenko.storage.service.api.resource;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import org.telyatenko.storage.service.api.dto.ProductDto;
import org.telyatenko.storage.service.api.exception.ResponseError;

import java.util.List;
import java.util.UUID;

@Tag(name = "product resource", description = "Интерфейс взаимодействия с Products")
@RequestMapping
public interface ProductResource {

    @Operation(summary = "Вызов всего списка товаров")
    @GetMapping("/api/v1/products")
    List<ProductDto> products(ProductDto productDto);

    @Operation(summary = "Вызов товара по id")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Данного товара нет на складе",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseError.class))))
    })
    @GetMapping("/api/v1/product/{id}")
    ProductDto getProductById(@PathVariable("id") UUID id);

    @Operation(summary = "Создание нового товара и добавление его в базу данных")
    @PostMapping("/api/v1/product")
    ProductDto createProduct(@RequestBody ProductDto productDto);

    @Operation(summary = "Удаление товара из базы данных по id")
    @DeleteMapping("/api/v1/product/{id}")
    void deleteProduct(@PathVariable("id") UUID id);

    @Operation(summary = "Изменение параметров товара")
    @PatchMapping("/api/v1/product/{id}")
    ProductDto updateProduct(@PathVariable("id") UUID id, @RequestBody ProductDto productDto);
}
