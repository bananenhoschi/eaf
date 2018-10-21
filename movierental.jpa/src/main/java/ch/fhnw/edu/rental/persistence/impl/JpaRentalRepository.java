package ch.fhnw.edu.rental.persistence.impl;

import ch.fhnw.edu.rental.model.Rental;
import ch.fhnw.edu.rental.model.User;
import ch.fhnw.edu.rental.persistence.RentalRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaRentalRepository implements RentalRepository {


    @PersistenceContext
    protected EntityManager em;

    @Override
    public Optional<Rental> findById(Long id) {
        return Optional.ofNullable(em.find(Rental.class, id));
    }

    @Override
    public List<Rental> findAll() {
        TypedQuery<Rental> q = em.createQuery("SELECT r FROM Rental r", Rental.class);
        return q.getResultList();
    }

    @Override
    public Rental save(Rental t) {
        return em.merge(t);
    }

    @Override
    public void deleteById(Long id) {
        int isSuccessful = em.createQuery("delete from Rental r where r.id=:id")
                .setParameter("id", id)
                .executeUpdate();
        if(isSuccessful == 0)
            throw new IllegalArgumentException("Could not delete Rental with id " + id);
    }

    @Override
    public void delete(Rental entity) {
        em.remove(em.contains(entity) ? entity : em.merge(entity));
    }

    @Override
    public boolean existsById(Long id) {
        TypedQuery<Long> q = em.createQuery(
                "SELECT  COUNT(r) FROM Rental r WHERE r.id = :id", Long.class);
        q.setParameter("id", id);
        return q.getSingleResult() > 0;
    }

    @Override
    public long count() {
        return em.createQuery("SELECT COUNT(r) FROM Rental r", Long.class)
                .getSingleResult();
    }

    @Override
    public List<Rental> findByUser(User user) {
        return null;
    }
}
