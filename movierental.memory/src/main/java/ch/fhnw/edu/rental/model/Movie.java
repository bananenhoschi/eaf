package ch.fhnw.edu.rental.model;

import java.time.LocalDate;
import java.util.Objects;

public class Movie {
	private Long id;
	
	private final String title;
	private final LocalDate releaseDate;
	private boolean rented;
	private PriceCategory priceCategory;


	public Movie( String title, LocalDate releaseDate,  PriceCategory priceCategory) throws NullPointerException {
		if ((title == null) || (releaseDate == null) || (priceCategory == null)) {
			throw new NullPointerException("not all input parameters are set!");
		}
		this.title = title;
		this.releaseDate = releaseDate;
		this.priceCategory = priceCategory;
		this.rented = false;
	}

	public Movie(long id, String title, LocalDate releaseDate, boolean rented,  PriceCategory priceCategory) throws NullPointerException {
		if ((title == null) || (releaseDate == null) || (priceCategory == null)) {
			throw new NullPointerException("not all input parameters are set!");
		}
		this.id = id;
		this.title = title;
		this.releaseDate = releaseDate;
		this.priceCategory = priceCategory;
		this.rented = rented;
	}
	
	public PriceCategory getPriceCategory() {
		return priceCategory;
	}

	public void setPriceCategory(PriceCategory priceCategory) {
		this.priceCategory = priceCategory;
	}

	public String getTitle() {
		return title;
	}
	
	public LocalDate getReleaseDate() {
		return releaseDate;
	}
	
	public boolean isRented() {
		return rented;
	}

	public void setRented(boolean rented) {
		this.rented = rented;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Movie movie = (Movie) o;
		return rented == movie.rented &&
				Objects.equals(id, movie.id) &&
				Objects.equals(title, movie.title) &&
				Objects.equals(releaseDate, movie.releaseDate) &&
				Objects.equals(priceCategory, movie.priceCategory);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title, releaseDate, rented, priceCategory);
	}
}
