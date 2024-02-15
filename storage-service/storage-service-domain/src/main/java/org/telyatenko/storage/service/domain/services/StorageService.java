package org.telyatenko.storage.service.domain.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telyatenko.storage.service.domain.models.Storage;
import org.telyatenko.storage.service.domain.repositories.StorageRepository;
import java.time.OffsetTime;
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
        return storageRepository.findById(id).orElseThrow();
    }

    public Storage saveStorage(Storage storage) {
        log.info("Saving new {}", storage);
        return storageRepository.save(storage);
    }

    public void deleteStorage(UUID id) {
        storageRepository.deleteById(id);
    }

    public Storage updateStorage(UUID id, String name, OffsetTime startWork, OffsetTime finishWork) {
        storageRepository.updateStorage(id, name, startWork, finishWork);
        return storageRepository.findById(id).orElseThrow();
    }
}



