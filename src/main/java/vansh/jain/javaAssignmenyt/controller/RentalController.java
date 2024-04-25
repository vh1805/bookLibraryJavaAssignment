package vansh.jain.javaAssignmenyt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vansh.jain.javaAssignmenyt.entity.Rental;
import vansh.jain.javaAssignmenyt.services.RentalService;

@RestController
@RequestMapping("/rental")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @PostMapping("/create")
    public ResponseEntity<?> createRentalRecords(@RequestBody Rental rental) {
        ResponseEntity<?> responseEntity = rentalService.createRentalRecords(rental);
        return ResponseEntity.status(responseEntity.getStatusCode()).body(responseEntity.getBody());
    }

    @GetMapping("/get")
    public ResponseEntity<?> getRentalRecords() {
        return rentalService.getRentalRecords();
    }

    @GetMapping("/byAuthor/{authorName}")
    public ResponseEntity<?> getBooksByAuthor(@PathVariable("authorName") String authorName) {
        return rentalService.getBooksByAuthor(authorName);
    }

    @GetMapping("/books/available")
    public ResponseEntity<?> getAvailableBooks() {
        return rentalService.getAvailableBooks();
    }

    @GetMapping("/books/currentRented")
    public ResponseEntity<?> getBooksCurrentlyRented() {
        return rentalService.getBooksCurrentlyRented();
    }
}
