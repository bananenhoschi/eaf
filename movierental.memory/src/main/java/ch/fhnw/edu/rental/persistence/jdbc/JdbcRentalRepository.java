package ch.fhnw.edu.rental.persistence.jdbc;

import ch.fhnw.edu.rental.model.Movie;
import ch.fhnw.edu.rental.model.Rental;
import ch.fhnw.edu.rental.model.User;
import ch.fhnw.edu.rental.persistence.MovieRepository;
import ch.fhnw.edu.rental.persistence.RentalRepository;
import ch.fhnw.edu.rental.persistence.UserRepository;
import ch.fhnw.edu.rental.persistence.impl.RentalRepositoryImpl;
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
public class JdbcRentalRepository extends RentalRepositoryImpl {

    @Autowired
    private JdbcTemplate template;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MovieRepository movieRepository;


    @Override
    public List<Rental> findByUser(User user) {
        return template.query("SELECT * FROM RENTALS where USER_ID = ?", (rs, row) -> createRental(rs, user), user.getId());
    }

    @Override
    public Optional<Rental> findById(Long id) {
        return template.query("SELECT * FROM RENTALS where RENTAL_ID = ?", (rs, row) -> createRental(rs), id).stream().findFirst();
    }

    @Override
    public List<Rental> findAll() {
        return template.query("SELECT * FROM RENTALS ", (rs, row) -> createRental(rs));
    }

    @Override
    public Rental save(Rental rental) {

        if (rental.getId() == null) {

            KeyHolder holder = new GeneratedKeyHolder();

            template.update(new PreparedStatementCreator() {

                @Override
                public PreparedStatement createPreparedStatement(Connection connection)
                        throws SQLException {
                    PreparedStatement ps = connection.prepareStatement("INSERT INTO RENTALS (RENTAL_RENTALDATE, RENTAL_RENTALDAYS, USER_ID, MOVIE_ID) VALUES (?,?,?,?)",
                            Statement.RETURN_GENERATED_KEYS);
                    ps.setDate(1, Date.valueOf(rental.getRentalDate()));
                    ps.setInt(2, rental.getRentalDays());
                    ps.setLong(3, rental.getUser().getId());
                    ps.setLong(4, rental.getUser().getId());
                    return ps;
                }
            }, holder);
            rental.setId(holder.getKey().longValue());
        } else {

            template.update("UPDATE RENTALS SET movie_id = ?, USER_ID = ?, rental_rentaldate = ?, rental_rentaldays = ? WHERE RENTAL_ID = ?",
                    rental.getMovie().getId(),
                    rental.getUser().getId(),
                    rental.getRentalDate(),
                    rental.getRentalDays(),
                    rental.getId());
        }
        return rental;
    }

    @Override
    public void deleteById(Long aLong) {
        template.update("DELETE FROM RENTALS WHERE RENTAL_ID = ?", aLong);
    }

    @Override
    public void delete(Rental entity) {
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

    public Rental createRental(ResultSet rs, User user) throws SQLException {

        long movieId = rs.getLong("MOVIE_ID");
        long userId = rs.getLong("USER_ID");

        if (user == null) {
            user = userRepository.findById(userId).get();
        }

        Movie movie = movieRepository.findById(movieId).get();

        Rental rental = new Rental(user,movie
                , rs.getInt("rental_rentaldays"));
        rental.setId(rs.getLong("RENTAL_ID"));
        return rental;

    }

    public Rental createRental(ResultSet rs) throws SQLException {
        return createRental(rs, null);
    }
}
