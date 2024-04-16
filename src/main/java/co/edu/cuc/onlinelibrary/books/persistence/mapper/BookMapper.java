package co.edu.cuc.onlinelibrary.books.persistence.mapper;

import co.edu.cuc.onlinelibrary.books.domain.dto.BookDto;
import co.edu.cuc.onlinelibrary.books.persistence.entity.BookEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookEntity toBookEntity(BookDto dto);
    BookDto toBookDto(BookEntity entity);
}
