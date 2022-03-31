package mk.ukim.finki.emt.lab.lab.model.exceptions;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Long id) {
        super(String.format("Book with id: %d is not found", id));
    }
}
