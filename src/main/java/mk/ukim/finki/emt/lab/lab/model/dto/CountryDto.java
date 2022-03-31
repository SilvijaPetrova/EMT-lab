package mk.ukim.finki.emt.lab.lab.model.dto;

public class CountryDto {

    private String name;

    private String continent;

    public CountryDto(String name, String continent) {
        this.name = name;
        this.continent = continent;
    }

    public CountryDto() {
    }

    public String getName() {
        return name;
    }

    public String getContinent() {
        return continent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }
}
