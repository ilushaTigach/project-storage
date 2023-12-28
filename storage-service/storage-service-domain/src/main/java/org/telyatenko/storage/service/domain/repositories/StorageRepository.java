package org.telyatenko.storage.service.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.telyatenko.storage.service.domain.models.Storage;

import java.util.List;

public interface StorageRepository extends JpaRepository<Storage, Long> {
    List<Storage> findByNameStorage(String nameStorage);
}
