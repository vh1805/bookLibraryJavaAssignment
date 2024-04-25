package vansh.jain.javaAssignmenyt.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vansh.jain.javaAssignmenyt.constants.AuthorConstant;
import vansh.jain.javaAssignmenyt.entity.Author;
import vansh.jain.javaAssignmenyt.repository.AuthorRepository;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService{
    @Autowired
    private AuthorRepository authorRepository;
    @Override
    public ResponseEntity<?> createAuthor(Author author) {
        try {
            String authorName = author.getName();
            Author isAuthorExist = authorRepository.findByName(authorName);
            if(isAuthorExist==null) {
                authorRepository.save(author);
                return new ResponseEntity<>(AuthorConstant.AUTHOR_CREATED_SUCCESSFULLY,HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<>(AuthorConstant.AUTHOR_ALREADY_EXIST,HttpStatus.OK);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(AuthorConstant.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Override
    public ResponseEntity<?> getAllAuthor() {
        try {
            List<Author> authors = authorRepository.findAll();
            return new ResponseEntity<>(authors, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(AuthorConstant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getAuthorByName(String name) {
        Author author = authorRepository.findByName(name);
        if (author != null) {
            return new ResponseEntity<>(author, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(AuthorConstant.AUTHOR_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteAuthorByName(String name) {
        try {
            Author authorDelete = authorRepository.findByName(name);
            if(authorDelete!=null) {
                authorRepository.deleteByName(name);
                return new ResponseEntity<>(AuthorConstant.AUTHOR_DELETED_SUCCESSFULLY,HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(AuthorConstant.AUTHOR_NOT_FOUND,HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(AuthorConstant.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> updateAuthorByName(String name,Author author) {
        try {
            Author authorUpdate = authorRepository.findByName(name);
            if(authorUpdate!=null) {
                if(author.getBiography() != null) {
                    authorUpdate.setBiography(author.getBiography());
                }
                authorRepository.save(authorUpdate);
                return new ResponseEntity<>(AuthorConstant.AUTHOR_UPDATED_SUCCESSFULLY,HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(AuthorConstant.AUTHOR_NOT_FOUND,HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(AuthorConstant.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
