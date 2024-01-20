package org.telyatenko.storage.service.domain.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.telyatenko.storage.service.domain.models.Storage;
import org.telyatenko.storage.service.domain.services.StorageService;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class StorageController {

    private final StorageService storageService;

    @GetMapping("/api/v1/storages") //Дополнителеньный маппер если вдруг захочется посмотреть все склады
    public List<Storage> storages(@RequestParam(name = "name", required = false) String name) {
        return storageService.listStorages(name);
    }

    @GetMapping("/api/v1/storage/{id}")
    public Storage getStorageById(@PathVariable("id") UUID id) {
        return storageService.getById(id);
    }

    @PostMapping("/api/v1/storage")
    public UUID createStorage(@RequestBody Storage storage) {
        return storageService.saveStorage(storage);
    }

    @DeleteMapping("/api/v1/storage/{id}")
    public void deleteStorage(@PathVariable UUID id) {
        storageService.deleteStorage(id);
    }

    @PatchMapping("/api/v1/storage/{id}")
    public Storage updateStorage(@PathVariable("id") UUID id, @RequestBody Storage storage) {
        return storageService.updateStorage(id, storage.getName(), storage.getStartWork(), storage.getFinishWork());
    }
}
