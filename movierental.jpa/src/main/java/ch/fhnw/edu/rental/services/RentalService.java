package ch.fhnw.edu.rental.services;

import ch.fhnw.edu.rental.model.Rental;

import java.util.List;

public interface RentalService {
    public Rental getRentalById(Long id);

    public List<Rental> getAllRentals();

    public void deleteRental(Rental rental);
}
