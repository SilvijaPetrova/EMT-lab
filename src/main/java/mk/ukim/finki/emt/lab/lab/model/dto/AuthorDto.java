package mk.ukim.finki.emt.lab.lab.model.dto;

public class AuthorDto {

    private String name;

    private String surname;

    private Long country;

    public AuthorDto(String name, String surname, Long country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
    }

    public AuthorDto() {
    }

    public String getName() {
        return name;
    }

    public String getSurename() {
        return surname;
    }

    public Long getCountry() {
        return country;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurename(String surname) {
        this.surname = surname;
    }

    public void setCountry(Long country) {
        this.country = country;
    }
}
