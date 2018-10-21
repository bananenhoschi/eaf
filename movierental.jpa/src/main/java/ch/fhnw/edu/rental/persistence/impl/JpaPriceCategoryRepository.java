package ch.fhnw.edu.rental.persistence.impl;

import ch.fhnw.edu.rental.model.Movie;
import ch.fhnw.edu.rental.model.PriceCategory;
import ch.fhnw.edu.rental.persistence.PriceCategoryRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaPriceCategoryRepository implements PriceCategoryRepository {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public Optional<PriceCategory> findById(Long id) {
        return Optional.ofNullable(em.find(PriceCategory.class, id));
    }

    @Override
    public List<PriceCategory> findAll() {
        TypedQuery<PriceCategory> q = em.createQuery("SELECT p FROM PriceCategory p", PriceCategory.class);
        return q.getResultList();
    }

    @Override
    public PriceCategory save(PriceCategory t) {
        return em.merge(t);
    }

    @Override
    public void deleteById(Long id) {
        int isSuccessful = em.createQuery("delete from PriceCategory r where r.id=:id")
                .setParameter("id", id)
                .executeUpdate();
        if(isSuccessful == 0)
            throw new IllegalArgumentException("Could not delete PriceCategory with id " + id);
    }


    @Override
    public void delete(PriceCategory entity) {
        em.remove(entity);
    }

    @Override
    public boolean existsById(Long id) {
        TypedQuery<Long> q = em.createQuery(
                "SELECT COUNT(p) FROM PriceCategory p WHERE p.id = :id", Long.class);
        q.setParameter("id", id);
        return q.getSingleResult() > 0;
    }

    @Override
    public long count() {
        return em.createQuery("SELECT COUNT(p) FROM PriceCategory p", Long.class).getSingleResult();
    }

}
