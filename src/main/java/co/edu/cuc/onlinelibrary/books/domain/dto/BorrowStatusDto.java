package co.edu.cuc.onlinelibrary.books.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class BorrowStatusDto implements Serializable {
    private Integer id;
    private String description;
}
