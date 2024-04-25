package vansh.jain.javaAssignmenyt.services;

import org.springframework.http.ResponseEntity;
import vansh.jain.javaAssignmenyt.entity.Rental;

public interface RentalService {
    ResponseEntity<?> createRentalRecords(Rental rental);

    ResponseEntity<?> getRentalRecords();

    ResponseEntity<?> getBooksByAuthor(String authorName);

    ResponseEntity<?> getAvailableBooks();

    ResponseEntity<?> getBooksCurrentlyRented();
}
