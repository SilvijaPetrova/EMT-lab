package mk.ukim.finki.emt.lab.lab.service.impl;

import mk.ukim.finki.emt.lab.lab.model.Author;
import mk.ukim.finki.emt.lab.lab.model.Country;
import mk.ukim.finki.emt.lab.lab.model.dto.AuthorDto;
import mk.ukim.finki.emt.lab.lab.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.emt.lab.lab.model.exceptions.CountryNotFoundException;
import mk.ukim.finki.emt.lab.lab.repository.AuthorRepository;
import mk.ukim.finki.emt.lab.lab.repository.CountryRepository;
import mk.ukim.finki.emt.lab.lab.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public Optional<Author> findByNameAndSurname(String name, String surname) {
        return this.authorRepository.findByNameAndSurname(name, surname);
    }

    @Override
    public Optional<Author> save(String name, String surname, Long countryId) {
        Country country = this.countryRepository.findById(countryId)
                .orElseThrow(() -> new CountryNotFoundException(countryId));

        this.authorRepository.deleteByNameAndSurname(name, surname);
        Author author = new Author(name, surname, country);
        this.authorRepository.save(author);

        return Optional.of(author);
    }

    @Override
    public Optional<Author> save(AuthorDto authorDto) {
        Country country = this.countryRepository.findById(authorDto.getCountry())
                .orElseThrow(() -> new CountryNotFoundException(authorDto.getCountry()));

        this.authorRepository.deleteByNameAndSurname(authorDto.getName(), authorDto.getSurename());
        Author author = new Author(authorDto.getName(), authorDto.getSurename(), country);
        this.authorRepository.save(author);

        return Optional.of(author);
    }

    @Override
    public Optional<Author> edit(Long id, String name, String surname, Long countryId) {
        Author author = this.authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));

        author.setName(name);
        author.setSurename(surname);

        Country country = this.countryRepository.findById(countryId)
                .orElseThrow(() -> new CountryNotFoundException(countryId));
        author.setCountry(country);

        this.authorRepository.save(author);
        return Optional.of(author);
    }

    @Override
    public Optional<Author> edit(Long id, AuthorDto authorDto) {
        Author author = this.authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));

        author.setName(authorDto.getName());
        author.setSurename(authorDto.getSurename());

        Country country = this.countryRepository.findById(authorDto.getCountry())
                .orElseThrow(() -> new CountryNotFoundException(authorDto.getCountry()));
        author.setCountry(country);

        this.authorRepository.save(author);
        return Optional.of(author);
    }

    @Override
    public void deleteById(Long id) {
        this.authorRepository.deleteById(id);
    }
}
