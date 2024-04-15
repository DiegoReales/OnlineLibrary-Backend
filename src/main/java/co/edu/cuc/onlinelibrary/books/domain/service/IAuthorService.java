package co.edu.cuc.onlinelibrary.books.domain.service;

import co.edu.cuc.onlinelibrary.books.domain.dto.AuthorDto;
import co.edu.cuc.onlinelibrary.books.domain.dto.BookDto;
import co.edu.cuc.onlinelibrary.books.domain.dto.request.AuthorRequestBody;
import co.edu.cuc.onlinelibrary.books.domain.dto.request.BookRequestBody;

import java.util.List;

public interface IAuthorService {
    List<AuthorDto> findAll();

    AuthorDto findById(Integer bookId);

    AuthorDto create(AuthorRequestBody req);
    AuthorDto update(AuthorRequestBody req, Integer authorId);
    void deleteById(Integer authorId);
}
