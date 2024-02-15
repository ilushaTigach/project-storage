package org.telyatenko.storage.service.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.OffsetTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Сущность склада")
public class StorageDto {

    @Schema(description = "Идентификатор склада", example = "38d42f1b-4b11-49a6-9106-6ee486fb15aa")
    private UUID id;

    @Schema(description = "Название склада")
    private String name;

    @Schema(description = "Размер склада")
    private long size;

    @Schema(description = "Текущая заполненность склада")
    private long sizeNow;

    @Schema(description = "Начало рабочего дня")
    private OffsetTime startWork;

    @Schema(description = "Конец рабочего дня")
    private OffsetTime finishWork;

    @Schema(description = "Продукты расположеные на складе")
    private List<ProductDto> products;
}
