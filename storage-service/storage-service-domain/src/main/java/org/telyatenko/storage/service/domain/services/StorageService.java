package org.telyatenko.storage.service.domain.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telyatenko.storage.service.domain.models.Storage;
import org.telyatenko.storage.service.domain.repositories.StorageRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class StorageService {

//    private long ID;
//
//    private List<Storage> storages = new ArrayList<>();
//
//    {
//        storages.add(new Storage(++ID, "SamaraStorage", 100000, 10, BY_TIME,
//                OffsetTime.parse("10:00:00+00:00"),
//                OffsetTime.parse("23:00:00+00:00")));
//    }
    //сравниваем c LocalTime.NOW
    //mapstruckt для преобразования объектов в друг друга

    private final StorageRepository storageRepository;

    public List<Storage> listStorages(String nameStorage) {
        if (nameStorage != null) return storageRepository.findByNameStorage(nameStorage);
        return storageRepository.findAll();
    }

    public void saveStorage(Storage storage) {
        log.info("Saving new {}", storage);//спросить про логирование
        storageRepository.save(storage);
    }

    public void deleteStorage(Long id) {
        storageRepository.deleteById(id);
    }
}


