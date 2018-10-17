package ch.fhnw.edu.rental.persistence.impl;

import ch.fhnw.edu.rental.model.Movie;
import ch.fhnw.edu.rental.persistence.MovieRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaMovieRepository implements MovieRepository {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public Optional<Movie> findById(Long id) {
        return Optional.ofNullable(em.find(Movie.class, id));
    }

    @Override
    public List<Movie> findAll() {
        TypedQuery<Movie> q = em.createQuery("SELECT m FROM Movie m", Movie.class);
        return q.getResultList();
    }

    @Override
    public Movie save(Movie t) {
        return em.merge(t);
    }

    @Override
    public void deleteById(Long id) {
        em.remove(em.getReference(Movie.class, id));
    }

    @Override
    public void delete(Movie entity) {
        em.remove(entity);
    }

    @Override
    public boolean existsById(Long id) {
        TypedQuery<Long> q = em.createQuery(
                "SELECT COUNT(m) FROM Movie m WHERE m.id = :id", Long.class);
        q.setParameter("id", id);
        return q.getSingleResult() > 0;
    }

    @Override
    public long count() {
        return em.createQuery("SELECT COUNT(m) FROM Movie m", Long.class)
                .getSingleResult();
    }

    @Override
    public List<Movie> findByTitle(String title) {
        TypedQuery<Movie> q = em.createQuery(
                "SELECT m FROM Movie m WHERE m.title = :title", Movie.class);
        q.setParameter("title", title);
        return q.getResultList();
    }


}
