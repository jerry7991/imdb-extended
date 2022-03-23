package com.imdb.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imdb.entity.Movie;
import com.imdb.entity.MovieEntity;
import com.imdb.repositry.ImdbRepository;

@Service
public class ImdbService {

	@Autowired
	private ImdbRepository imdbRepository;

	public List<Movie> getJsopData() throws IOException {
		final Document documents = Jsoup.connect("https://www.imdb.com/chart/top/").get();
		List<Movie> list = new ArrayList<>();
		for (Element row : documents.select("table.chart.full-width tr")) {
			String movieName = row.select(".titleColumn a").text();
			if (movieName.isEmpty())
				continue;
			String casts = row.select(".titleColumn a").attr("title");
			double rating = Double.parseDouble(row.select(".ratingColumn.imdbRating strong").text());
			list.add(new Movie(movieName, rating, casts));
		}
		return list;
	}

	@PostConstruct
	public void loadImdbDataToLocal() {
		try {
			getJsopData().forEach(movie -> {
				movie.getCasts().forEach(cast -> imdbRepository.addMovie(cast,
						new MovieEntity(movie.getMovieName(), movie.getRating())));
			});
		} catch (IOException e) {
			System.out.println("Exception is :: " + e.getMessage());
		}
	}

	public List<MovieEntity> getMoviesByCastName(String castName) {
		return imdbRepository.getMoviesByCastName(castName);
	}

	public List<MovieEntity> getTopNmoviesByCastName(String castName, int topN) {
		return imdbRepository.getTopNmoviesByCastName(castName, topN);
	}
}
