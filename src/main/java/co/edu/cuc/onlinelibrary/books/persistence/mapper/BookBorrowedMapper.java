package co.edu.cuc.onlinelibrary.books.persistence.mapper;

import co.edu.cuc.onlinelibrary.books.domain.dto.BookBorrowedDto;
import co.edu.cuc.onlinelibrary.books.persistence.entity.BookBorrowedEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookBorrowedMapper {

    BookBorrowedEntity toBookBorrowedEntity(BookBorrowedDto dto);
    BookBorrowedDto toBookBorrowedDto(BookBorrowedEntity entity);
}
