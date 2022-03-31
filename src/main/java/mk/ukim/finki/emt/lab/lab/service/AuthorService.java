package mk.ukim.finki.emt.lab.lab.service;

import mk.ukim.finki.emt.lab.lab.model.Author;
import mk.ukim.finki.emt.lab.lab.model.dto.AuthorDto;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAll();

    Optional<Author> findById(Long id);

    Optional<Author> findByNameAndSurname(String name, String surname);

    Optional<Author> save(String name, String surname, Long countryId);

    Optional<Author> save(AuthorDto authorDto);

    Optional<Author> edit(Long id, String name, String surname, Long countryId);

    Optional<Author> edit(Long id, AuthorDto authorDto);

    void deleteById(Long id);
}
