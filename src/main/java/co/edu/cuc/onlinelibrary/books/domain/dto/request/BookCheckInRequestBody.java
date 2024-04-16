package co.edu.cuc.onlinelibrary.books.domain.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookCheckInRequestBody {
    private Integer bookBorrowedId;
    private LocalDateTime checkIn;
}
