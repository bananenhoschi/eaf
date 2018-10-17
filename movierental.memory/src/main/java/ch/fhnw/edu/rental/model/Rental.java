package ch.fhnw.edu.rental.model;

import java.time.LocalDate;
import java.util.Objects;


public class Rental {
	private Long id;
	
	private Movie movie;
	private User user;
	private LocalDate rentalDate;
	private int rentalDays;
	
	public Rental(User user, Movie movie, int rentalDays) {
		this(user, movie, rentalDays, LocalDate.now());
	}
	
	public Rental(User user, Movie movie, int rentalDays, LocalDate rentalDate) {
		if (user == null || movie == null || rentalDays <= 0) {
			throw new NullPointerException("not all input parameters are set!" + user + "/" + movie + "/" + rentalDays);
		}
		if (movie.isRented()) {
			throw new IllegalStateException("movie is already rented!");
		}
		this.user = user;
		user.getRentals().add(this);
		this.movie = movie;
		this.rentalDays = rentalDays;
		this.rentalDate = rentalDate;
	}
	
	public double getRentalFee() {
		return movie.getPriceCategory().getCharge(rentalDays);
	}

	public Long getId() {
		return id;
	}

	public Movie getMovie() {
		return movie;
	}

	public User getUser() {
		return user;
	}

	public LocalDate getRentalDate() {
		return rentalDate;
	}

	public int getRentalDays() {
		return rentalDays;
	}

	public void setId(Long id) {
		this.id = id;
	}	

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setRentalDate(LocalDate rentalDate) {
		this.rentalDate = rentalDate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Rental rental = (Rental) o;
		return rentalDays == rental.rentalDays &&
				Objects.equals(id, rental.id) &&
				Objects.equals(movie, rental.movie) &&
				Objects.equals(user, rental.user) &&
				Objects.equals(rentalDate, rental.rentalDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, movie, user, rentalDate, rentalDays);
	}

	public void setRentalDays(int rentalDays) {
		this.rentalDays = rentalDays;
	}

}
