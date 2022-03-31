package mk.ukim.finki.emt.lab.lab.service.impl;

import mk.ukim.finki.emt.lab.lab.model.Country;
import mk.ukim.finki.emt.lab.lab.model.dto.CountryDto;
import mk.ukim.finki.emt.lab.lab.model.exceptions.CountryNotFoundException;
import mk.ukim.finki.emt.lab.lab.repository.CountryRepository;
import mk.ukim.finki.emt.lab.lab.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return this.countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return this.countryRepository.findById(id);
    }

    @Override
    public Optional<Country> findByName(String name) {
        return this.countryRepository.findByName(name);
    }

    @Override
    public Optional<Country> save(String name, String continent) {
        this.countryRepository.deleteByName(name);
        Country country = new Country(name, continent);
        this.countryRepository.save(country);

        return Optional.of(country);
    }

    @Override
    public Optional<Country> save(CountryDto countryDto) {
        this.countryRepository.deleteByName(countryDto.getName());
        Country country = new Country(countryDto.getName(), countryDto.getContinent());
        this.countryRepository.save(country);

        return Optional.of(country);
    }

    @Override
    public Optional<Country> edit(Long id, String name, String continent) {
        Country country = this.countryRepository.findById(id).orElseThrow(() -> new CountryNotFoundException(id));

        country.setName(name);
        country.setContinent(continent);

        this.countryRepository.save(country);
        return Optional.of(country);
    }

    @Override
    public Optional<Country> edit(Long id, CountryDto countryDto) {
        Country country = this.countryRepository.findById(id).orElseThrow(() -> new CountryNotFoundException(id));

        country.setName(country.getName());
        country.setContinent(country.getContinent());

        this.countryRepository.save(country);
        return Optional.of(country);
    }

    @Override
    public void deleteById(Long id) {
        this.countryRepository.deleteById(id);
    }
}
