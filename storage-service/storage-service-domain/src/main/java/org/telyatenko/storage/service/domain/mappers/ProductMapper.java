package org.telyatenko.storage.service.domain.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.telyatenko.storage.service.api.dto.ProductDto;
import org.telyatenko.storage.service.domain.models.Product;
import org.telyatenko.storage.service.domain.services.StorageService;
import java.util.List;

@Mapper
public abstract class ProductMapper {

    @Autowired
    protected StorageService storageService;

    public abstract List<ProductDto> toDto(List<Product> products);

    @Mapping(target = "storageId", source = "storage.id")
    public abstract ProductDto toDto(Product product);

    @Mapping(target = "storage", expression = "java(storageService.getById(productDto.getStorageId()))")
    public abstract Product toEntity(ProductDto productDto);
}
