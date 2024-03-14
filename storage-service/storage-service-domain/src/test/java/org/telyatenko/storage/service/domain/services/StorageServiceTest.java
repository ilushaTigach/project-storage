package org.telyatenko.storage.service.domain.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telyatenko.storage.service.api.exception.ConflictStorageException;
import org.telyatenko.storage.service.api.exception.NotFoundStorageException;
import org.telyatenko.storage.service.domain.models.Storage;
import org.telyatenko.storage.service.domain.repositories.StorageRepository;
import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StorageServiceTest {

    @Mock
    StorageRepository storageRepository;

    @InjectMocks
    StorageService storageService;

    @Test
    public void testGetByIdValidStorage() {
        UUID id = UUID.randomUUID();
        Storage storage = new Storage()
                .setId(id)
                .setStartWork(LocalTime.now().minusHours(1))
                .setFinishWork(LocalTime.now().plusHours(1));

        when(storageRepository.findById(id)).thenReturn(Optional.of(storage));

        Storage result = storageService.getById(id);

        assertEquals(storage, result);
    }

    @Test
    public void testGetByIdNotFound() {
        UUID id = UUID.randomUUID();

        when(storageRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundStorageException.class, () -> storageService.getById(id));
    }

    @Test
    public void testGetByIdConflict() {
        UUID id = UUID.randomUUID();
        Storage storage = new Storage()
                .setId(id)
                .setStartWork(LocalTime.now())
                .setFinishWork(LocalTime.now());

        when(storageRepository.findById(id)).thenReturn(Optional.of(storage));

        assertThrows(ConflictStorageException.class, () -> storageService.getById(id));
    }

    @Test
    @DisplayName("Тест на удаление хранилища без продуктов и в рабочее время")
    public void testDeleteStorage_NoProducts_InWorkingHours() {
        UUID id = UUID.randomUUID();
        Storage storage = new Storage()
                .setId(id)
                .setStartWork(LocalTime.now().minusHours(1))
                .setFinishWork(LocalTime.now().plusHours(1));

        when(storageRepository.findById(id)).thenReturn(Optional.of(storage));

        storageService.deleteStorage(id);

        verify(storageRepository).deleteById(id);
    }

    @Test
    @DisplayName("Тест на удаление хранилища с продуктами")
    public void testDeleteStorage_WithProducts() {
        UUID id = UUID.randomUUID();
        Storage storage = new Storage()
                .setId(id)
                .setStartWork(LocalTime.now())
                .setFinishWork(LocalTime.now());

        when(storageRepository.findById(id)).thenReturn(Optional.of(storage));

        assertThrows(RuntimeException.class, () -> storageService.deleteStorage(id));
    }

    @Test
    @DisplayName("Тест на удаление хранилища в нерабочее время")
    public void testDeleteStorage_OutsideWorkingHours() {
        UUID id = UUID.randomUUID();
        Storage storage = new Storage()
                .setId(id)
                .setStartWork(LocalTime.now())
                .setFinishWork(LocalTime.now());

        when(storageRepository.findById(id)).thenReturn(Optional.of(storage));

        assertThrows(ConflictStorageException.class, () -> storageService.deleteStorage(id));
    }

    @Test
    @DisplayName("Тест на удаление несуществующего хранилища")
    public void testDeleteStorage_NonExistingStorage() {
        UUID id = UUID.randomUUID();

        when(storageRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundStorageException.class, () -> storageService.deleteStorage(id));
    }

    @Test
    @DisplayName("Тест на успешное обновление хранилища в рабочее время")
    public void testUpdateStorage_SuccessfulUpdate_InWorkingHours() {
        UUID id = UUID.randomUUID();
        LocalTime currentTime = LocalTime.now();
        Storage storage = new Storage()
                .setId(id)
                .setName("NewName")
                .setSizeNow(100L)
                .setStartWork(LocalTime.of(8, 0))
                .setFinishWork(LocalTime.of(23, 0));

        when(storageRepository.findById(id)).thenReturn(Optional.of(storage));

        Storage updatedStorage = storageService.updateStorage(id, "NewName", 100L, LocalTime.of(8, 0), LocalTime.of(23, 0));

        assertEquals("NewName", updatedStorage.getName());
        assertEquals(100L, updatedStorage.getSizeNow());
        assertEquals(LocalTime.of(8, 0), updatedStorage.getStartWork());
        assertEquals(LocalTime.of(23, 0), updatedStorage.getFinishWork());
    }

    @Test
    @DisplayName("Тест на конфликт при обновлении хранилища в нерабочее время")
    public void testUpdateStorage_ConflictOutsideWorkingHours() {
        UUID id = UUID.randomUUID();
        LocalTime currentTime = LocalTime.now();
        Storage storage = new Storage()
                .setId(id)
                .setStartWork(currentTime.plusHours(1));

        when(storageRepository.findById(id)).thenReturn(Optional.of(storage));

        assertThrows(ConflictStorageException.class, () -> storageService.updateStorage(id, "NewName", 100L, LocalTime.of(8, 0), LocalTime.of(17, 0)));
    }

    @Test
    @DisplayName("Тест на обновление несуществующего хранилища")
    public void testUpdateStorage_NonExistingStorage() {
        UUID id = UUID.randomUUID();

        when(storageRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundStorageException.class, () -> storageService.updateStorage(id, "NewName", 100L, LocalTime.of(8, 0), LocalTime.of(17, 0)));
    }
}