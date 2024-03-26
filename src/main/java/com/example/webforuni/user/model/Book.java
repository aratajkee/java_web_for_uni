package com.example.webforuni.user.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.core.io.Resource;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator")
    private Integer id;

    @Column(name = "name")
    String name;

    @Column(name = "author")
    String author;

    @Column
    String fileName;

    @Lob
    @Column(name = "file")
    byte[] file;

    @Lob
    @Column(name="cover")
    byte[] cover;

    public String generateBase64Image() {
        return Base64.encodeBase64String(this.cover);
    }
}
