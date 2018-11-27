package ch.fhnw.edu.rental.persistence;

import ch.fhnw.edu.rental.model.PriceCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceCategoryRepository extends JpaRepository<PriceCategory, Long> {
}
