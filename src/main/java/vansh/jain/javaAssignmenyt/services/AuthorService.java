package vansh.jain.javaAssignmenyt.services;

import org.springframework.http.ResponseEntity;
import vansh.jain.javaAssignmenyt.entity.Author;

import java.util.List;

public interface AuthorService {
    ResponseEntity<?> createAuthor(Author author);

    ResponseEntity<?> getAllAuthor();

    ResponseEntity<?> getAuthorByName(String name);

    ResponseEntity<?> deleteAuthorByName(String name);

    ResponseEntity<?> updateAuthorByName(String name,Author author);
}
