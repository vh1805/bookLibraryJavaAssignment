package vansh.jain.javaAssignmenyt.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rental")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name ="book_id", nullable = true)
    private Book book;

    @Column(name = "Renter Name")
    private String renterName;

    @Column(name = "Rental Date")
    @Temporal(TemporalType.DATE)
    private Date rentalDate;

    @Column(name = "Return Date")
    @Temporal(TemporalType.DATE)
    private Date returnDate;
}
