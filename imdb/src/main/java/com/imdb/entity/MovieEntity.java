package com.imdb.entity;

public class MovieEntity {
	private String name;
	private double rating;

	public MovieEntity(String name, double rating) {
		super();
		this.name = name;
		this.rating = rating;
	}

	public String getName() {
		return name;
	}

	public double getRating() {
		return rating;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}
}
