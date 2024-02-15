package org.telyatenko.storage.service.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Сущность товара")
public class ProductDto {

    @Schema(description = "Идентификатор товара", example = "38d42f1b-4b11-49a6-9106-6ee486fb15aa")
    private UUID id;

    @Schema(description = "Название товара")
    private String title;

    @Schema(description = "Описание товара")
    private String description;

    @Schema(description = "Цена товара")
    private int price;

    @Schema(description = "Продавец товара")
    private String author;

    @Schema(description = "Склад на котором располагается товар")
    private UUID storageId;

    public ProductDto(UUID id, ProductDto dto) {
    }
}
