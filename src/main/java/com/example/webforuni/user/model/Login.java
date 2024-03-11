package com.example.webforuni.user.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "login_records")
public class Login {
    @Id
    @Column(name = "id")
    Integer id;

    @Column(name = "count")
    Integer counter;

    @Column(name = "userEmail")
    String userEmail;

    @Column(name = "role")
    String role;

    public Login(int id, int logVal) {
        this.id = id;
        this.counter = logVal;
    }
}
