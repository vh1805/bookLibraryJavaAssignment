package vansh.jain.javaAssignmenyt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vansh.jain.javaAssignmenyt.entity.Author;
import vansh.jain.javaAssignmenyt.services.AuthorService;


@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/create")
    public ResponseEntity<?> createAuthor(@RequestBody Author author) {
        ResponseEntity<?> responseEntity = authorService.createAuthor(author);
        return ResponseEntity.status(responseEntity.getStatusCode()).body(responseEntity.getBody());
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAllAuthor() {
        return authorService.getAllAuthor();
    }

    @GetMapping("/get/{name}")
    public ResponseEntity<?> getAuthorByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(authorService.getAuthorByName(name), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<?> deleteAuthorByName(@PathVariable("name") String name) {
        return authorService.deleteAuthorByName(name);
    }

    @PutMapping("/update/{name}")
    public ResponseEntity<?> updateAuthorByName(@PathVariable("name") String name,@RequestBody Author author) {
        return authorService.updateAuthorByName(name,author);
    }
}
