package org.telyatenko.storage.service.domain.dto;

import lombok.Data;
import org.telyatenko.storage.service.domain.models.Product;
import org.telyatenko.storage.service.domain.models.ScheduleType;
import java.time.OffsetTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class StorageDto {

    private UUID id;

    private String nameStorage;

    private long size; //размер склада

    private long sizeNow; //текущая заполненность склада

    private ScheduleType scheduleType;

    private OffsetTime startWork;

    private OffsetTime finishWork;

    private List<Product> products = new ArrayList<>();
}
