package co.edu.cuc.onlinelibrary.books.persistence;

import co.edu.cuc.onlinelibrary.books.domain.dto.AuthorDto;
import co.edu.cuc.onlinelibrary.books.domain.repository.IAuthorRepository;
import co.edu.cuc.onlinelibrary.books.persistence.crudrespository.AuthorCrudRepository;
import co.edu.cuc.onlinelibrary.books.persistence.entity.AuthorEntity;
import co.edu.cuc.onlinelibrary.books.persistence.mapper.AuthorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuthorRepository implements IAuthorRepository {

    private final AuthorCrudRepository authorCrudRepository;
    private final AuthorMapper authorMapper;

    @Override
    public List<AuthorDto> findAll() {
        List<AuthorEntity> entites = (List<AuthorEntity>) authorCrudRepository.findAll();
        return entites.stream().map(authorMapper::toAuthorDto).toList();
    }

    @Override
    public Optional<AuthorDto> findById(Integer authorId) {
        return authorCrudRepository.findById(authorId).map(authorMapper::toAuthorDto);
    }

    @Override
    public AuthorDto save(AuthorDto author) {
        AuthorEntity entity = authorMapper.toAuthorEntity(author);
        return authorMapper.toAuthorDto(authorCrudRepository.save(entity));
    }
}
