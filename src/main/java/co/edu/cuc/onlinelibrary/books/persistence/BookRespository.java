package co.edu.cuc.onlinelibrary.books.persistence;

import co.edu.cuc.onlinelibrary.books.domain.dto.BookDto;
import co.edu.cuc.onlinelibrary.books.domain.repository.IBookRepository;
import co.edu.cuc.onlinelibrary.books.persistence.crudrespository.BookCrudRepository;
import co.edu.cuc.onlinelibrary.books.persistence.entity.BookEntity;
import co.edu.cuc.onlinelibrary.books.persistence.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class BookRespository implements IBookRepository {

    private final BookCrudRepository bookCrudRepository;
    private final BookMapper bookMapper;

    @Override
    public List<BookDto> findAll() {
        List<BookEntity> entities = (List<BookEntity>) bookCrudRepository.findAll();
        return entities.stream().map(bookMapper::toBookDto).toList();
    }

    @Override
    public List<BookDto> findAvailable() {
        List<BookEntity> entities = bookCrudRepository.findByStatus(1);
        return entities.stream().map(bookMapper::toBookDto).toList();
    }

    @Override
    public Optional<BookDto> findById(Integer bookId) {
        return bookCrudRepository.findById(bookId).map(bookMapper::toBookDto);
    }

    @Override
    public BookDto save(BookDto book) {
        BookEntity entity = bookMapper.toBookEntity(book);
        return bookMapper.toBookDto(bookCrudRepository.save(entity));
    }
}
