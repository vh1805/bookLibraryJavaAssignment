package vansh.jain.javaAssignmenyt.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vansh.jain.javaAssignmenyt.entity.Rental;


@Repository
public interface RentalRepository extends JpaRepository<Rental,Long> {

}
