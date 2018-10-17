package ch.fhnw.edu.rental.model;

import javax.persistence.*;

@Entity(name = "PriceCategory")
@Table(name = "PRICECATEGORIES")
@DiscriminatorColumn(name="PRICECATEGORY_TYPE")
public abstract class PriceCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRICECATEGORY_ID")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public abstract double getCharge(int daysRented);

    public int getFrequentRenterPoints(int daysRented) {
        return 1;
    }
}
