package vansh.jain.javaAssignmenyt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vansh.jain.javaAssignmenyt.constants.AuthorConstant;
import vansh.jain.javaAssignmenyt.constants.BookConstant;
import vansh.jain.javaAssignmenyt.constants.RentalConstants;
import vansh.jain.javaAssignmenyt.entity.Author;
import vansh.jain.javaAssignmenyt.entity.Book;
import vansh.jain.javaAssignmenyt.entity.Rental;
import vansh.jain.javaAssignmenyt.repository.AuthorRepository;
import vansh.jain.javaAssignmenyt.repository.BookRepository;
import vansh.jain.javaAssignmenyt.repository.RentalRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RentalServiceImpl implements RentalService{

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private AuthorRepository authorRepository;


    @Autowired
    private BookRepository bookRepository;
    @Override
    public ResponseEntity<?> createRentalRecords(Rental rental) {
        try {
            Long bookId = rental.getBook().getId();
            Optional<Book> isBookExist = bookRepository.findById(bookId);
            if(isBookExist.isPresent()) {
                rentalRepository.save(rental);
                return new ResponseEntity<>(RentalConstants.RENTAL_CREATED_SUCCESSFULLY,HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<>(BookConstant.BOOK_NOT_FOUND,HttpStatus.NOT_FOUND);
            }

        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(RentalConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getRentalRecords() {
        try {
            List<Rental> rentals = rentalRepository.findAll();
            return new ResponseEntity<>(rentals,HttpStatus.OK);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(RentalConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getBooksByAuthor(String authorName) {
        try {
            Author author = authorRepository.findByName(authorName);
            if(author!=null) {
                List<Book> books = bookRepository.findByAuthorName(authorName);
                return new ResponseEntity<>(books,HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(AuthorConstant.AUTHOR_NOT_FOUND,HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(RentalConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getAvailableBooks() {
        try {
            List<Book> availableBooks = new ArrayList<>();
            List<Book> allBooks = bookRepository.findAll();
            List<Rental> rentalList = rentalRepository.findAll();
            for(Book book : allBooks) {
                boolean isBookAvailable = true;
                for(Rental rental : rentalList) {
                    if(book.getId() == rental.getBook().getId()) {
                        isBookAvailable=false;
                        break;
                    }
                }
                if(isBookAvailable == true) {
                    availableBooks.add(book);
                }
            }
            return new ResponseEntity<>(availableBooks, HttpStatus.OK);

        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(RentalConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getBooksCurrentlyRented() {
        try {
            List<Book> booksCurrentlyRented = new ArrayList<>();
            List<Book> allBooks = bookRepository.findAll();
            List<Rental> rentalList = rentalRepository.findAll();

            for (Book book : allBooks) {
                boolean isBookRented = false;
                for (Rental rental : rentalList) {
                    if (book.getId()==rental.getBook().getId()) {
                        isBookRented = true;
                        break;
                    }
                }
                if (isBookRented == true) {
                    booksCurrentlyRented.add(book);
                }
            }
            return new ResponseEntity<>(booksCurrentlyRented,HttpStatus.OK);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(RentalConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
