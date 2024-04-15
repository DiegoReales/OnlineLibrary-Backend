package co.edu.cuc.onlinelibrary.books.persistence.mapper;

import co.edu.cuc.onlinelibrary.books.domain.dto.AuthorDto;
import co.edu.cuc.onlinelibrary.books.persistence.entity.AuthorEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorEntity toAuthorEntity(AuthorDto dto);
    AuthorDto toAuthorDto(AuthorEntity entity);
}
