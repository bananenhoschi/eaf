package ch.fhnw.edu.rental.persistence.impl;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.fhnw.edu.rental.model.Movie;
import ch.fhnw.edu.rental.persistence.MovieRepository;
import ch.fhnw.edu.rental.persistence.PriceCategoryRepository;

//@Component
public class MovieRepositoryImpl implements MovieRepository {
	private Map<Long, Movie> data = new HashMap<Long, Movie>();
	private long nextId = 1;

	@Autowired
	private PriceCategoryRepository priceCategoryRepo;
	
	@SuppressWarnings("unused")
	private void initData () {
		data.clear();
		nextId = 1;
		
		save(new Movie(1,"Marie Curie", LocalDate.of(2017, Month.JUNE, 2),false, priceCategoryRepo.findById(1L).get()));
		save(new Movie(2,"Curchill", LocalDate.of(2017, Month.SEPTEMBER, 20),false, priceCategoryRepo.findById(1L).get()));
		save(new Movie(3,"The Boss Baby", LocalDate.of(2017, Month.AUGUST, 3),false, priceCategoryRepo.findById(2L).get()));
		save(new Movie(4,"Pirates of the Caribean: Salazar's Revenge", LocalDate.of(2017,Month.OCTOBER, 2), false,priceCategoryRepo.findById(3L).get()));
		save(new Movie(5,"Die göttliche Ordnung", LocalDate.of(2017, Month.SEPTEMBER, 21),false, priceCategoryRepo.findById(1L).get()));

		save(new Movie(6,"Loving Vincent", LocalDate.of(2018, Month.MAY, 25),false, priceCategoryRepo.findById(1L).get()));
		save(new Movie(7,"Fast & Furious 7", LocalDate.of(2018, Month.AUGUST, 13), false,priceCategoryRepo.findById(3L).get()));
		save(new Movie(8,"Momo", LocalDate.of(2018, Month.OCTOBER, 1),false, priceCategoryRepo.findById(3L).get()));
		save(new Movie(9,"Swimming with Men", LocalDate.of(2018, Month.OCTOBER, 3), false,priceCategoryRepo.findById(3L).get()));
		save(new Movie(10,"Jurassic World", LocalDate.of(2018, Month.OCTOBER, 22), false,priceCategoryRepo.findById(3L).get()));
}
	
	@Override
	public Optional<Movie> findById(Long id) {
		if(id == null) throw new IllegalArgumentException();
		return Optional.ofNullable(data.get(id));
	}

	@Override
	public List<Movie> findAll() {
		return new ArrayList<Movie>(data.values());
	}

	@Override
	public List<Movie> findByTitle(String name) {
		List<Movie> result = new ArrayList<Movie>();
		for(Movie m : data.values()){
			if(m.getTitle().equals(name)) result.add(m);
		}
		return result;
	}

	@Override
	public Movie save(Movie movie) {
		if (movie.getId() == null)
			movie.setId(nextId++);
		data.put(movie.getId(), movie);
		return movie;
	}

	@Override
	public void delete(Movie movie) {
		if(movie == null) throw new IllegalArgumentException();
		data.remove(movie.getId());
		movie.setId(null);
	}

	@Override
	public void deleteById(Long id) {
		if(id == null) throw new IllegalArgumentException();
		findById(id).ifPresent(e -> delete(e));
	}

	@Override
	public boolean existsById(Long id) {
		if(id == null) throw new IllegalArgumentException();
		return data.get(id) != null;
	}

	@Override
	public long count() {
		return data.size();
	}

}
