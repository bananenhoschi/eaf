package ch.fhnw.edu.rental.persistence.jdbc;

import ch.fhnw.edu.rental.model.Movie;
import ch.fhnw.edu.rental.persistence.MovieRepository;
import ch.fhnw.edu.rental.persistence.PriceCategoryRepository;
import ch.fhnw.edu.rental.persistence.impl.MovieRepositoryImpl;
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
public class JdbcMovieRepository extends MovieRepositoryImpl {

    @Autowired
    private JdbcTemplate template;

    @Autowired
    private PriceCategoryRepository priceCategoryRepo;

    @Override
    public List<Movie> findByTitle(String title) {
        return template.query("SELECT * FROM MOVIES where MOVIE_TITLE = ?", (rs, row) -> createMovie(rs), title);
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return template.query("SELECT * FROM MOVIES where MOVIE_ID = ?", (rs, row) -> createMovie(rs), id).stream().findFirst();
    }

    @Override
    public List<Movie> findAll() {
        return template.query("SELECT * FROM MOVIES", (rs, row) -> createMovie(rs));
    }

    @Override
    public Movie save(Movie movie) {

        if (movie.getId() == null) {

            KeyHolder holder = new GeneratedKeyHolder();

            template.update(new PreparedStatementCreator() {

                @Override
                public PreparedStatement createPreparedStatement(Connection connection)
                        throws SQLException {
                    PreparedStatement ps = connection.prepareStatement("INSERT INTO MOVIES (MOVIE_TITLE, MOVIE_RELEASEDATE, MOVIE_RENTED, PRICECATEGORY_FK) VALUES (?,?,?,?)",
                            Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, movie.getTitle());
                    ps.setDate(2, Date.valueOf(movie.getReleaseDate()));
                    ps.setBoolean(3, movie.isRented());
                    ps.setLong(4, movie.getPriceCategory().getId());
                    return ps;
                }
            }, holder);
            movie.setId(holder.getKey().longValue());
            return movie;

        } else {

            template.update("UPDATE MOVIES " +
                            "SET MOVIE_TITLE = ?, " +
                            "    MOVIE_RELEASEDATE = ?, " +
                            "    MOVIE_RENTED = ?, " +
                            "    PRICECATEGORY_FK = ?" +
                            "    WHERE MOVIE_ID = ?"
                    , movie.getTitle()
                    , movie.getReleaseDate()
                    , movie.isRented()
                    , movie.getPriceCategory().getId()
                    , movie.getId());
        }
        return movie;

    }

    @Override
    public void deleteById(Long id) {
        template.update("DELETE FROM MOVIES WHERE MOVIE_ID = ?", id);
    }

    @Override
    public void delete(Movie entity) {
        deleteById(entity.getId());
    }

    @Override
    public boolean existsById(Long id) {
        return findById(id).isPresent();
    }

    @Override
    public long count() {
        return findAll().stream().count();
    }

    private Movie createMovie(ResultSet rs) throws SQLException {
        long priceCategoryId = rs.getLong("PRICECATEGORY_FK");
        return new Movie(rs.getLong("MOVIE_ID"),
                rs.getString("MOVIE_TITLE"),
                rs.getDate("MOVIE_RELEASEDATE").toLocalDate(),
                rs.getBoolean("MOVIE_RENTED"),
                priceCategoryRepo.findById(priceCategoryId).get());
    }
}
