package ch.fhnw.edu.rental.persistence.impl;

import ch.fhnw.edu.rental.model.Movie;
import ch.fhnw.edu.rental.persistence.MovieRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaMovieRepository implements MovieRepository {

    @Override
    public Optional<Movie> findById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Movie> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Movie save(Movie t) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(Movie entity) {
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
    public List<Movie> findByTitle(String title) {
        // TODO Auto-generated method stub
        return null;
    }


}
