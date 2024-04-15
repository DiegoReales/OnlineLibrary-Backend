package co.edu.cuc.onlinelibrary.books.domain.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequestBody {
    private Integer authorId;
    private String isbn;
    private String title;
    private String description;
    private Integer stock;
    private Integer available;
    private Integer status;
}
