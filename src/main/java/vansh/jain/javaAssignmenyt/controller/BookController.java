package vansh.jain.javaAssignmenyt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vansh.jain.javaAssignmenyt.entity.Book;
import vansh.jain.javaAssignmenyt.services.BookService;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/create")
    public ResponseEntity<?> createBook(@RequestBody Book book) {
        ResponseEntity<?> responseEntity = bookService.createBook(book);
        return ResponseEntity.status(responseEntity.getStatusCode()).body(responseEntity.getBody());
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAllBook() {
        return bookService.getAllBook();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBookById(@PathVariable("id") Long bookId) {
        return bookService.deleteBookById(bookId);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBookById(@PathVariable("id") Long bookId,@RequestBody Book book) {
        return bookService.updateBookById(bookId,book);
    }
}
