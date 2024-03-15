package org.telyatenko.storage.service.api.resource;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import org.telyatenko.storage.service.api.dto.StorageDto;
import java.util.List;
import java.util.UUID;

@Tag(name = "storage resourse", description = "Интерфейс взаимодействия со Storages")
@RequestMapping
public interface StorageResource {

    @Operation(summary = "Вызов всего списка складов")
    @GetMapping("api/v1/storages")
    List<StorageDto> storages();

    @Operation(summary = "Вызов склада по id")
    @GetMapping("/api/v1/storage/{id}")
    StorageDto getStorageById(@PathVariable("id") UUID id);

    @Operation(summary = "Создание нового склада и добавление его в базу данных")
    @PostMapping("/api/v1/storage")
    StorageDto createStorage(@RequestBody StorageDto storageDto);

    @Operation(summary = "Удаление склада из базы данных по id")
    @DeleteMapping("/api/v1/storage/{id}")
    void deleteStorage(@PathVariable UUID id);

    @Operation(summary = "Изменение параметров склада")
    @PatchMapping("/api/v1/storage/{id}")
    StorageDto updateStorage(@PathVariable("id") UUID id, @RequestBody StorageDto storageDto);
}
