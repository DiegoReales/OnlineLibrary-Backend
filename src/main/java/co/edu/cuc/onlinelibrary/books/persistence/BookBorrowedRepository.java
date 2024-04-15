package co.edu.cuc.onlinelibrary.books.persistence;

import co.edu.cuc.onlinelibrary.books.domain.dto.BookBorrowedDto;
import co.edu.cuc.onlinelibrary.books.domain.repository.IBookBorrowedRepository;
import co.edu.cuc.onlinelibrary.books.persistence.crudrespository.BookBorrowedCrudRepository;
import co.edu.cuc.onlinelibrary.books.persistence.entity.BookBorrowedEntity;
import co.edu.cuc.onlinelibrary.books.persistence.mapper.BookBorrowedMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookBorrowedRepository implements IBookBorrowedRepository {

    private final BookBorrowedCrudRepository bookBorrowedCrudRepository;
    private final BookBorrowedMapper bookBorrowedMapper;

    @Override
    public List<BookBorrowedDto> findAll() {
        List<BookBorrowedEntity> entities = (List<BookBorrowedEntity>) bookBorrowedCrudRepository.findAll();
        return entities.stream().map(bookBorrowedMapper::toBookBorrowedDto).toList();
    }

    @Override
    public Optional<BookBorrowedDto> findById(Integer bookBorrowedId) {
        return bookBorrowedCrudRepository.findById(bookBorrowedId).map(bookBorrowedMapper::toBookBorrowedDto);
    }

    @Override
    public BookBorrowedDto save(BookBorrowedDto bookBorrowedDto) {
        BookBorrowedEntity entity = bookBorrowedMapper.toBookBorrowedEntity(bookBorrowedDto);
        return bookBorrowedMapper.toBookBorrowedDto(bookBorrowedCrudRepository.save(entity));
    }
}
