package org.telyatenko.storage.service.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.telyatenko.storage.service.domain.models.Storage;

import java.time.LocalTime;
import java.time.OffsetTime;
import java.util.List;
import java.util.UUID;

public interface StorageRepository extends JpaRepository<Storage, UUID> {

    List<Storage> findByName(String name);

    @Modifying
    @Transactional
    @Query(value = """ 
            UPDATE storages
            SET name = COALESCE(NULLIF(?2, ''), name),
            size_now = COALESCE(NULLIF(?3, 0), size_now),
            start_work = COALESCE(NULLIF(?4, NULL)\\:\\:time, start_work),
            finish_work = COALESCE(NULLIF(?5, NULL)\\:\\:time, finish_work)
            WHERE id = ?1""", nativeQuery = true)
    void updateStorage(UUID id, String name, Long sizeNow, LocalTime startWork, LocalTime finishWork);

    @Modifying
    @Transactional
    @Query(value = """ 
            UPDATE storages
            SET size_now = COALESCE(NULLIF(?2, 0), size_now)
            WHERE id = ?1""", nativeQuery = true)
    void updateStorageSizeNow(UUID id, Long sizeNow);
}


