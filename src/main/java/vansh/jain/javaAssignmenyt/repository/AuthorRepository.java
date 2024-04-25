package vansh.jain.javaAssignmenyt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vansh.jain.javaAssignmenyt.entity.Author;
@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {

    Author findByName(String name);

    void deleteByName(String name);
}
