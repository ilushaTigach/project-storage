package org.telyatenko.storage.service.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.OffsetTime;
import java.util.ArrayList;
import java.util.List;

@Entity //показывает что это не просто класс а класс который эмулирует таблицу из БД
@Table(name = "storages")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Storage {


    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "nameStorage")
    private String nameStorage;
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
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "storage_id")
    private List<Product> products = new ArrayList<>();

}

