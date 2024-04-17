package co.edu.cuc.onlinelibrary.books.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto implements Serializable {
    private Integer id;
    private String username;
    private String name;
    private String lastname;
    private String dni;
}
