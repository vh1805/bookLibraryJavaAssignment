package vansh.jain.javaAssignmenyt.services;

import org.springframework.http.ResponseEntity;
import vansh.jain.javaAssignmenyt.entity.Book;

public interface BookService {
    ResponseEntity<?> createBook(Book book);

    ResponseEntity<?> getAllBook();

    ResponseEntity<?> deleteBookById(Long bookId);

    ResponseEntity<?> updateBookById(Long bookId,Book book);
}
