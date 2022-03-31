package mk.ukim.finki.emt.lab.lab.repository;

import mk.ukim.finki.emt.lab.lab.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    Optional<Country> findByName(String name);

    void deleteByName(String name);
}
