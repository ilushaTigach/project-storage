package org.telyatenko.storage.service.domain.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telyatenko.storage.service.api.exception.ConflictStorageException;
import org.telyatenko.storage.service.api.exception.NotFoundStorageException;
import org.telyatenko.storage.service.domain.models.Storage;
import org.telyatenko.storage.service.domain.repositories.StorageRepository;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class StorageService {

    private final StorageRepository storageRepository;

    public List<Storage> listStorages() {
        return storageRepository.findAll();
    }

    public Storage getById(UUID id) {
        LocalTime currentTime = LocalTime.now();
        Storage storage = storageRepository.findById(id).orElseThrow(() -> new NotFoundStorageException("id", id.toString()));
        if (currentTime.isBefore(storage.getStartWork()) || currentTime.isAfter(storage.getFinishWork())){
            throw new ConflictStorageException();
        } else {
            return storage;
        }
    }

    public Storage saveStorage(Storage storage) {
        log.info("Saving new {}", storage);
        return storageRepository.save(storage);
    }

    public void deleteStorage(UUID id) {
        LocalTime currentTime = LocalTime.now();
        Storage storage = storageRepository.findById(id).orElseThrow(() -> new NotFoundStorageException("id", id.toString()));
        if (currentTime.isBefore(storage.getStartWork()) || currentTime.isAfter(storage.getFinishWork())) {
            throw new ConflictStorageException();
        } else if (storage.getProducts() == null || storage.getProducts().isEmpty()){
            storageRepository.deleteById(id);
        } else {
            throw new RuntimeException("There are products in the storage");
        }
    }

    public Storage updateStorage(UUID id, String name, Long sizeNow, LocalTime startWork, LocalTime finishWork) {
        LocalTime currentTime = LocalTime.now();
        if (currentTime.isBefore(getById(id).getStartWork()) || currentTime.isAfter(getById(id).getFinishWork())){
            throw new ConflictStorageException();
        } else {
        storageRepository.updateStorage(id, name, sizeNow, startWork, finishWork);
        return storageRepository.findById(id).orElseThrow();
        }
    }

    public void updateStorageSizeNow(UUID id, Long sizeNow) {
        storageRepository.updateStorageSizeNow(id, sizeNow);
    }
}



