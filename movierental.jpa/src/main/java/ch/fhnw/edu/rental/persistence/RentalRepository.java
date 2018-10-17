package ch.fhnw.edu.rental.persistence;

import ch.fhnw.edu.rental.model.Rental;
import ch.fhnw.edu.rental.model.User;

import java.util.List;

public interface RentalRepository extends Repository<Rental, Long>{
    List<Rental> findByUser(User user);
}
