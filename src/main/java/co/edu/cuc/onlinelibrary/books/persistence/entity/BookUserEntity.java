package co.edu.cuc.onlinelibrary.books.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class BookUserEntity {

    @Id
    @Column(name = "user_id")
    private Integer id;
    private String username;
    private String firstname;
    private String secondname;
    private String firstsurname;
    private String secondsurname;
    private String dni;
}
