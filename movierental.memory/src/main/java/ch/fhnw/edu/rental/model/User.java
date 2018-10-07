package ch.fhnw.edu.rental.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
	private Long id;

	private String lastName;
	private String firstName;
	private String email;
	private List<Rental> rentals;

	public User(String lastName, String firstName) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.rentals = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String name) {
		this.lastName = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Rental> getRentals() {
		return rentals;
	}

	public void setRentals(List<Rental> rentals) {
		this.rentals = rentals;
	}

	public int getCharge() {
		int result = 0;
		for (Rental rental : rentals) {
			result += rental.getMovie().getPriceCategory().getCharge(rental.getRentalDays());
		}
		return result;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return Objects.equals(id, user.id) &&
				Objects.equals(lastName, user.lastName) &&
				Objects.equals(firstName, user.firstName) &&
				Objects.equals(email, user.email) &&
				Objects.equals(rentals, user.rentals);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, lastName, firstName, email, rentals);
	}
}
