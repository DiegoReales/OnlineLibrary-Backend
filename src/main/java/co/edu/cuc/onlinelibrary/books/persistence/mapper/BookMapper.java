package co.edu.cuc.onlinelibrary.books.persistence.mapper;

import co.edu.cuc.onlinelibrary.books.domain.dto.BookDto;
import co.edu.cuc.onlinelibrary.books.persistence.entity.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookEntity toBookEntity(BookDto dto);
    BookDto toBookDto(BookEntity entity);
}
