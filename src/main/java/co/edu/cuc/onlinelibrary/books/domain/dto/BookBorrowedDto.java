package co.edu.cuc.onlinelibrary.books.domain.dto;

import co.edu.cuc.onlinelibrary.auth.domain.dto.AuditorDto;
import co.edu.cuc.onlinelibrary.auth.domain.dto.UserDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookBorrowedDto extends AuditorDto {
    private Integer id;
    private Integer bookId;
    private Integer userId;
    private Integer checkOut;
    private Integer checkIn;
    private Integer status;

    private BookDto book;
    private UserDto user;
    private BookStatusDto bookStatus;
}
