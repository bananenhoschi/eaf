package ch.fhnw.edu.rental.persistence.jdbc;

import ch.fhnw.edu.rental.model.Rental;
import ch.fhnw.edu.rental.model.User;
import ch.fhnw.edu.rental.persistence.MovieRepository;
import ch.fhnw.edu.rental.persistence.RentalRepository;
import ch.fhnw.edu.rental.persistence.UserRepository;
import ch.fhnw.edu.rental.persistence.impl.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@Component
public class JdbcUserRepository extends UserRepositoryImpl {

    @Autowired
    private JdbcTemplate template;

    @Autowired
    private JdbcRentalRepository rentalRepository;


    private User createUser(ResultSet rs) throws SQLException {

        User user = new User(
                rs.getString("USER_NAME"),
                rs.getString("USER_FIRSTNAME"));
        user.setEmail(rs.getString("USER_EMAIL"));
        user.setId(rs.getLong("USER_ID"));

        user.setRentals(rentalRepository.findByUser(user));

        return user;
    }

    @Override
    public List<User> findByLastName(String lastName) {
        return template.query("SELECT * FROM USERS where USER_NAME = ?", (rs, row) -> createUser(rs), lastName);
    }


    @Override
    public List<User> findByFirstName(String firstName) {
        return template.query("SELECT * FROM USERS where USER_FIRSTNAME = ?", (rs, row) -> createUser(rs), firstName);
    }

    @Override
    public List<User> findByEmail(String email) {
        return template.query("SELECT * FROM USERS where USER_EMAIL = ?", (rs, row) -> createUser(rs), email);
    }

    @Override
    public Optional<User> findById(Long id) {
        return template.query("SELECT * FROM USERS where USER_ID = ?", (rs, row) -> createUser(rs), id).stream().findFirst();
    }

    @Override
    public List<User> findAll() {
        return template.query("SELECT * FROM USERS", (rs, row) -> createUser(rs));
    }

    @Override
    public User save(User user) {


        if (user.getId() == null) {

            KeyHolder holder = new GeneratedKeyHolder();

            template.update(new PreparedStatementCreator() {

                @Override
                public PreparedStatement createPreparedStatement(Connection connection)
                        throws SQLException {
                    PreparedStatement ps = connection.prepareStatement("INSERT INTO USERS (USER_NAME, USER_FIRSTNAME, USER_EMAIL) VALUES (?,?,?)",
                            Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, user.getLastName());
                    ps.setString(2, user.getFirstName());
                    ps.setString(3, user.getEmail());
                    return ps;
                }
            }, holder);
            user.setId(holder.getKey().longValue());
        } else {

            template.update("UPDATE USERS SET USER_NAME = ?, USER_FIRSTNAME = ?, USER_EMAIL = ? WHERE USER_ID = ?",
                    user.getLastName(),
                    user.getFirstName(),
                    user.getEmail(),
                    user.getId());
        }
        return user;
    }

    @Override
    public void deleteById(Long aLong) {
        template.update("DELETE FROM RENTALS WHERE USER_ID = ?", aLong);
        template.update("DELETE FROM USERS WHERE USER_ID = ?", aLong);
    }

    @Override
    public void delete(User entity) {
        deleteById(entity.getId());
    }

    @Override
    public boolean existsById(Long aLong) {
        return findById(aLong).isPresent();
    }

    @Override
    public long count() {
        return findAll().stream().count();
    }
}
