package org.telyatenko.storage.service.domain.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


@Entity //это не просто класс а класс который эмулирует таблицу из БД
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description", columnDefinition = "text")
    private String description;
    @Column(name = "price")
    private int price;
    @Column(name = "author")
    private String author;
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)

    private Storage storage;
}
