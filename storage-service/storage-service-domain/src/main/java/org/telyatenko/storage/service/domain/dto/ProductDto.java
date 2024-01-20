package org.telyatenko.storage.service.domain.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class ProductDto {

    private UUID id;

    private String title;

    private String description;

    private int price;

    private String author;

    private UUID storageId;
}
