package co.edu.cuc.onlinelibrary.books.domain.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookCheckOutRequestBody {
    private Integer bookId;
    private Integer userId;
}
