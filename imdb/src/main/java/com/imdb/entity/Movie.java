package com.imdb.entity;

import java.util.ArrayList;
import java.util.List;

public class Movie {
	private String movieName;
	private double rating;
	private List<String> casts;

	public Movie(String movieName, double rating, String casts) {
		this.movieName = movieName;
		this.rating = rating;
		this.casts = new ArrayList<>();
		for (String cast : casts.split(","))
			this.casts.add(cast.trim());
	}

	public List<String> getCasts() {
		return casts;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public void setCasts(List<String> casts) {
		this.casts = casts;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

}
