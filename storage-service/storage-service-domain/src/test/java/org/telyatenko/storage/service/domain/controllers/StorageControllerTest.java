package org.telyatenko.storage.service.domain.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telyatenko.storage.service.api.dto.StorageDto;
import org.telyatenko.storage.service.domain.mappers.StorageMapper;
import org.telyatenko.storage.service.domain.models.Storage;
import org.telyatenko.storage.service.domain.services.StorageService;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StorageControllerTest {

    @Mock
    private StorageService storageService;
    @Mock
    private StorageMapper storageMapper;

    @InjectMocks
    StorageController storageController;

    @Test
    void storages_shouldReturnStorageDto() {
        Storage testStorage1 = new Storage();
        testStorage1.setId(UUID.randomUUID());
        Storage testStorage2 = new Storage();
        testStorage2.setId(UUID.randomUUID());

        StorageDto testStorageDto1 = new StorageDto();
        testStorageDto1.setId(testStorage1.getId());
        StorageDto testStorageDto2 = new StorageDto();
        testStorageDto2.setId(testStorage2.getId());

        List<Storage> storageList = Arrays.asList(testStorage1, testStorage2);
        List<StorageDto> storageDtoList = Arrays.asList(testStorageDto1, testStorageDto2);

        when(storageService.listStorages()).thenReturn(storageList);
        when(storageMapper.toDtos(storageList)).thenReturn(storageDtoList);

        List<StorageDto> result = storageController.storages(new StorageDto());

        assertEquals(2, result.size());
        assertEquals(storageDtoList, result);
    }

    @Test
    void getStorageById_shouldReturnStorageDto() {
        UUID id = UUID.randomUUID();
        Storage testStorage = new Storage();
        testStorage.setId(id);
        StorageDto testStorageDto = new StorageDto();
        testStorageDto.setId(id);

        when(storageService.getById(id)).thenReturn(testStorage);
        when(storageMapper.toDto(testStorage)).thenReturn(testStorageDto);

        StorageDto result = storageController.getStorageById(id);

        assertEquals(testStorageDto, result);
    }

    @Test
    void createStorage() {
        UUID id = UUID.randomUUID();
        Storage testStorage = new Storage();
        testStorage.setId(id);
        StorageDto testStorageDto = new StorageDto();
        testStorageDto.setId(id);

        when(storageMapper.toEntity(testStorageDto)).thenReturn(testStorage);
        when(storageService.saveStorage(testStorage)).thenReturn(testStorage);
        when(storageMapper.toDto(testStorage)).thenReturn(testStorageDto);

        StorageDto result = storageController.createStorage(testStorageDto);

        assertEquals(testStorageDto, result);
    }
}