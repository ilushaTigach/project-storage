package org.telyatenko.storage.service.domain.mappers;

import org.mapstruct.Mapper;
import org.telyatenko.storage.service.api.dto.StorageDto;
import org.telyatenko.storage.service.domain.models.Storage;
import java.util.List;

@Mapper
public interface StorageMapper {

    List<StorageDto> toDtos(List<Storage> storages);

    StorageDto toDto(Storage storage);

    Storage toEntity(StorageDto storageDto);
}
