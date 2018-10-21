package ch.fhnw.edu.rental.persistence.impl;

import ch.fhnw.edu.rental.model.User;
import ch.fhnw.edu.rental.persistence.UserRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaUserRepository implements UserRepository {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(em.find(User.class, id));
    }

    @Override
    public List<User> findAll() {
        TypedQuery<User> q = em.createQuery("SELECT  u FROM User u", User.class);
        return q.getResultList();
    }

    @Override
    public User save(User t) {
        return em.merge(t);
    }

    @Override
    public void deleteById(Long id) {
        int isSuccessful = em.createQuery("delete from User r where r.id=:id")
                .setParameter("id", id)
                .executeUpdate();
        if(isSuccessful == 0)
            throw new IllegalArgumentException("Could not delete User with id " + id);
    }

    @Override
    public void delete(User entity) {
        em.remove(entity);
    }

    @Override
    public boolean existsById(Long id) {
        TypedQuery<Long> q = em.createQuery(
                "SELECT  COUNT(u) FROM User u WHERE u.id = :id", Long.class);
        q.setParameter("id", id);
        return q.getSingleResult() > 0;
    }

    @Override
    public long count() {
        return em.createQuery("SELECT COUNT(u) FROM User u", Long.class)
                .getSingleResult();
    }

    @Override
    public List<User> findByLastName(String lastName) {
        TypedQuery<User> q = em.createQuery(
                "SELECT u FROM User u WHERE u.lastName = :lastName", User.class);
        q.setParameter("lastName", lastName);
        return q.getResultList();
    }

    @Override
    public List<User> findByFirstName(String firstName) {
        TypedQuery<User> q = em.createQuery(
                "SELECT u FROM User u WHERE u.firstName = :firstName", User.class);
        q.setParameter("firstName", firstName);
        return q.getResultList();
    }

    @Override
    public List<User> findByEmail(String email) {
        TypedQuery<User> q = em.createQuery(
                "SELECT u FROM User u WHERE u.email = :email", User.class);
        q.setParameter("email", email);
        return q.getResultList();
    }

}
