package org.telyatenko.storage.service.domain.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.telyatenko.storage.service.api.dto.StorageDto;
import org.telyatenko.storage.service.api.resource.StorageResource;
import org.telyatenko.storage.service.domain.mappers.StorageMapper;
import org.telyatenko.storage.service.domain.models.Storage;
import org.telyatenko.storage.service.domain.services.StorageService;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class StorageController implements StorageResource {

    private final StorageService storageService;
    private final StorageMapper storageMapper;

    public List<StorageDto> storages(StorageDto storageDto) {
        List<Storage> storage = storageService.listStorages();
        return storageMapper.toDtos(storage);
    }

    public StorageDto getStorageById(@PathVariable("id") UUID id) {
        Storage storage = storageService.getById(id);
        return storageMapper.toDto(storage);
    }

    public StorageDto createStorage(@RequestBody StorageDto storageDto) {
        Storage storage = storageMapper.toEntity(storageDto);
        return storageMapper.toDto(storageService.saveStorage(storage));
    }

    public void deleteStorage(@PathVariable UUID id) {
        storageService.deleteStorage(id);
    }

    public StorageDto updateStorage(@PathVariable("id") UUID id, @RequestBody StorageDto storageDto) {
        Storage storage = storageService.updateStorage(id, storageDto.getName(), storageDto.getSizeNow(),
                storageDto.getStartWork(), storageDto.getFinishWork());
        return storageMapper.toDto(storage);
    }
}
