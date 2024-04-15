package co.edu.cuc.onlinelibrary.books.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookCheckOutRequestBody {
    private Integer bookId;
    private Integer userId;
}
