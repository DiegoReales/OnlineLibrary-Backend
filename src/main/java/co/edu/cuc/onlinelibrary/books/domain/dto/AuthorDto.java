package co.edu.cuc.onlinelibrary.books.domain.dto;

import co.edu.cuc.onlinelibrary.auth.domain.dto.AuditorDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AuthorDto extends AuditorDto {
    private Integer id;
    private String name;
    private String lastname;
    private Boolean active;

    private List<BookDto> books;
}
