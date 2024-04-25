package vansh.jain.javaAssignmenyt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vansh.jain.javaAssignmenyt.entity.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findByAuthorName(String authorName);

}
