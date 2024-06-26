package co.edu.cuc.onlinelibrary.books.domain.dto;

import co.edu.cuc.onlinelibrary.auth.domain.dto.AuditorDto;
import co.edu.cuc.onlinelibrary.books.domain.dto.request.BookRequestBody;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto extends AuditorDto {
    private Integer id;
    private Integer authorId;
    private String isbn;
    private String title;
    private String description;
    private Integer stock;
    private Integer available;
    private Integer status;
    private Boolean active;

    private AuthorDto author;
    private BookStatusDto bookStatus;

    public void fillFromRequestBody(BookRequestBody req) {
        this.authorId = req.getAuthorId();
        this.isbn = req.getIsbn();
        this.title = req.getTitle();
        this.description = req.getDescription();
        this.stock = req.getStock();
        this.available = req.getAvailable();
        this.status = req.getAvailable();
    }

    public void decrementAvailable() {
        this.available--;
        if (this.available == 0) {
            this.status = 2;
        }
    }


    public void incrementAvailable() {
        this.available++;
        if (this.available > 0) {
            this.status = 1;
        }
    }
}
