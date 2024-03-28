package com.example.webforuni.user.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator")
    @Column(name = "id")
    Integer id;

    @Column(name = "name")
    String name;

    @Pattern(regexp = "^\\w+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Неверный формат email!")
    @Column(name = "email")
    String email;

    @NotNull
    @Length(min = 3, max = 30)
    @Column(name = "password")
    String password;

    @Getter
    @Column(name = "role")
    String role;

    @ElementCollection(fetch = FetchType.EAGER)
    List<Integer> favoriteBooksId;

    @Lob
    @Column(name = "avatar")
    byte[] imageData;

    public void setRole(Role role) {
        this.role = role.toString();
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void addBook(int bookId) {
        this.favoriteBooksId.add(bookId);
    }

    public void deleteBook(int bookId) {
      this.favoriteBooksId.removeIf(i -> i == bookId);
    }

    public String generateBase64Image() {
        return Base64.encodeBase64String(this.imageData);
    }

    public enum Role {
        CLIENT("client"),
        MODERATOR("moderator"),
        ADMIN("admin"),
        PETUX("petuch");
        public final String role;
        Role(String role) {
            this.role = role;
        }
        @Override
        public String toString() {
            return this.role;
        }

        public static List<String> getValues() {
            List<String> roles = new ArrayList<>();
            for (Role role : Role.values()) {
                roles.add(role.toString());
            }
            return !roles.isEmpty()
                    ? roles
                    :null;
        }
    }

}
