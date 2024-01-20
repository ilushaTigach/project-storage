package org.telyatenko.storage.service.domain.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.OffsetTime;
import java.util.List;
import java.util.UUID;

@Entity //показывает что это не просто класс а класс который эмулирует таблицу из БД
@Table(name = "storages")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Storage {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "size")
    private long size; //размер склада

    @Column(name = "sizeNow")
    private long sizeNow; //текущая заполненность склада

    @Column(name = "scheduleType")
    private ScheduleType scheduleType;

    @Column(name = "startWork")
    private OffsetTime startWork;

    @Column(name = "finishWork")
    private OffsetTime finishWork;

    @JsonManagedReference
    @OneToMany(mappedBy = "storage",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products;

}

