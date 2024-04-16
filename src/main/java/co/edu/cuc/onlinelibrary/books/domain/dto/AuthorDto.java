package co.edu.cuc.onlinelibrary.books.domain.dto;

import co.edu.cuc.onlinelibrary.auth.domain.dto.AuditorDto;
import co.edu.cuc.onlinelibrary.books.domain.dto.request.AuthorRequestBody;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorDto extends AuditorDto {
    private Integer id;
    private String name;
    private String lastname;
    private Boolean active;

    public void fillFromRequestBody(AuthorRequestBody req) {
        this.name = req.getName();
        this.lastname = req.getLastname();
    }
}
