package org.telyatenko.storage.service.domain.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "storages")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true) //Делает для меня конструкторы с присваиванием
public class Storage {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "size")
    private long size;

    @Column(name = "sizeNow")
    private long sizeNow;

    @Column(name = "startWork")
    private LocalTime startWork;

    @Column(name = "finishWork")
    private LocalTime finishWork;

    @JsonManagedReference
    @OneToMany(mappedBy = "storage", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products;
}

