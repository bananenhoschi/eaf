package ch.fhnw.edu.rental.persistence;

import ch.fhnw.edu.rental.model.Movie;

import java.util.List;

public interface MovieRepository extends Repository<Movie, Long>{
    List<Movie> findByTitle(String title);
}
