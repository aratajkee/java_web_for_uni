package com.example.webforuni.user.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="news")
public class News {

    @Id
    @GenericGenerator(name="generator")
    @GeneratedValue(generator = "generator")
    @Column(name="id")
    private Long id;

    @Column(name="title")
    String title;

    @Column(name="text")
    String text;
}
