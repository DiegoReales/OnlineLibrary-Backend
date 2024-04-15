package co.edu.cuc.onlinelibrary.books.domain.dto;

import co.edu.cuc.onlinelibrary.auth.domain.dto.AuditorDto;
import co.edu.cuc.onlinelibrary.books.domain.dto.request.BookCheckOutRequestBody;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookBorrowedDto extends AuditorDto {
    private Integer id;
    private Integer bookId;
    private Integer userId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime checkOut;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime checkIn;

    private Integer status;

    private BookDto book;
    private UserDto user;
    private BorrowStatusDto borrowStatus;

    public void fillFromCheckOutRequestBody(BookCheckOutRequestBody req) {
        this.bookId = req.getBookId();
        this.userId = req.getUserId();
    }
}
