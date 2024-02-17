package org.telyatenko.storage.service.domain.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "storages")
@Getter
@Setter
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
    private long size;

    @Column(name = "sizeNow")
    private long sizeNow;

    @Column(name = "startWork")
    private OffsetTime startWork;

    @Column(name = "finishWork")
    private OffsetTime finishWork;

    @JsonManagedReference
    @OneToMany(mappedBy = "storage", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products;

}

