package vansh.jain.javaAssignmenyt.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vansh.jain.javaAssignmenyt.constants.AuthorConstant;
import vansh.jain.javaAssignmenyt.constants.BookConstant;
import vansh.jain.javaAssignmenyt.entity.Author;
import vansh.jain.javaAssignmenyt.entity.Book;
import vansh.jain.javaAssignmenyt.repository.AuthorRepository;
import vansh.jain.javaAssignmenyt.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public ResponseEntity<?> createBook(Book book) {
        try {
            String authorName = book.getAuthor().getName();
            Author isAuthorExist = authorRepository.findByName(authorName);
            if (isAuthorExist != null) {
                bookRepository.save(book);
                return new ResponseEntity<>(BookConstant.BOOK_CREATED_SUCCESSFULLY, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(AuthorConstant.AUTHOR_NOT_FOUND, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(BookConstant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getAllBook() {
        try {
            List<Book> books = bookRepository.findAll();
            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(BookConstant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> deleteBookById(Long bookId) {
        try {
            Optional<Book> isBookExist = bookRepository.findById(bookId);
            if (isBookExist.isPresent()) {
                bookRepository.deleteById(bookId);
                return new ResponseEntity<>(BookConstant.BOOK_DELETED_SUCCESSFULLY, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(BookConstant.BOOK_NOT_FOUND, HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(BookConstant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> updateBookById(Long bookId, Book book) {
        try {
            Optional<Book> optionalBook = bookRepository.findById(bookId);
            if (optionalBook.isPresent()) {
                Book bookUpdate = optionalBook.get();
                if (book.getIsbn() != null) {
                    bookUpdate.setIsbn(book.getIsbn());
                }
                if (book.getTitle() != null) {
                    bookUpdate.setTitle(book.getTitle());
                }
                if (book.getPublicationYear() != 0) {
                    bookUpdate.setPublicationYear(book.getPublicationYear());
                }
                bookRepository.save(bookUpdate);
                return new ResponseEntity<>(BookConstant.BOOK_UPDATED_SUCCESSFULLY, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(BookConstant.BOOK_NOT_FOUND, HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(BookConstant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

