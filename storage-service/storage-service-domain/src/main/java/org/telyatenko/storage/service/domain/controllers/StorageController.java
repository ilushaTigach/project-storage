package org.telyatenko.storage.service.domain.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.telyatenko.storage.service.domain.models.Storage;
import org.telyatenko.storage.service.domain.services.StorageService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StorageController {

    private final StorageService storageService;

    @GetMapping("/storage")
    public List<Storage> storages(@RequestParam(name = "nameStorage", required = false) String nameStorage) {
        return storageService.listStorages(nameStorage);
    }

    @PostMapping("/storage/create")
    public void createStorage(@RequestBody Storage storage) {
        storageService.saveStorage(storage);
    }

    @DeleteMapping("/storage/{id}/delete")
    public void deleteStorage(@PathVariable Long id) {
        storageService.deleteStorage(id);
    }
}
