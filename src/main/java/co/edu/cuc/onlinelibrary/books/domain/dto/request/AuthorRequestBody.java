package co.edu.cuc.onlinelibrary.books.domain.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorRequestBody {
    private String name;
    private String lastname;
}
