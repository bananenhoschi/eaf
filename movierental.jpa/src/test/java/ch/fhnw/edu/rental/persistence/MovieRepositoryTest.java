package ch.fhnw.edu.rental.persistence;

import ch.fhnw.edu.rental.model.Movie;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {"gui=false"})
@Transactional
public class MovieRepositoryTest {

    @Autowired
    private MovieRepository repo;

    private int totalNumberOfMovies;

    @Before
    public void setUp() {
        totalNumberOfMovies = repo.findAll().size();
    }

    @Test
    public void testCount() {
        assertEquals("result returned by count is not correct:", totalNumberOfMovies, repo.count());
    }

    @Test
    public void testExists() {
        assertTrue("movie with id 5 should exist in repo", repo.existsById(5L));
        assertFalse("movie with id 55 should not exist in repo", repo.existsById(55L));
    }

    @Test
    public void testDeleteId1() {
        repo.deleteById(5L);
        assertEquals("number of movies is not orrect after deleting movie with id 5: ", totalNumberOfMovies - 1, repo.count());
    }

    @Test
    public void testDeleteId2() {
        try {
            repo.deleteById(11L);
            fail("expected an exception when deleting a non-existing entity");
        } catch (Exception e) {
            // OK
        }
    }

    @Test
    public void findAll() {
        List<Movie> list = repo.findAll();
        assertEquals("number of movies returned by findAll is not correct:", totalNumberOfMovies, list.size());
        assertTrue(list.contains(repo.findById(1L).get()));
        assertTrue(list.contains(repo.findById(2L).get()));
        assertTrue(list.contains(repo.findById(3L).get()));
        assertTrue(list.contains(repo.findById(4L).get()));
        assertTrue(list.contains(repo.findById(5L).get()));
    }

}
