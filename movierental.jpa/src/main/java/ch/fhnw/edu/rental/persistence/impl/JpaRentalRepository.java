package ch.fhnw.edu.rental.persistence.impl;

import ch.fhnw.edu.rental.model.Rental;
import ch.fhnw.edu.rental.model.User;
import ch.fhnw.edu.rental.persistence.RentalRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaRentalRepository implements RentalRepository {

    @Override
    public Optional<Rental> findById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Rental> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Rental save(Rental t) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(Rental entity) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean existsById(Long id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<Rental> findByUser(User user) {
        // TODO Auto-generated method stub
        return null;
    }
}
