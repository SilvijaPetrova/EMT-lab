package mk.ukim.finki.emt.lab.lab.service;

import mk.ukim.finki.emt.lab.lab.model.Book;
import mk.ukim.finki.emt.lab.lab.model.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();

    Optional<Book> findById(Long id);

    Optional<Book> findByName(String name);

    Optional<Book> save(String name, String category, Long authorId, int availableCopies);

    Optional<Book> save(BookDto bookDto);

    Optional<Book> edit(Long id, String name, String category, Long authorId, int availableCopies);

    Optional<Book> edit(Long id, BookDto bookDto);

    void deleteById(Long id);
}
