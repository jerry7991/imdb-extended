package com.imdb.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.imdb.entity.Movie;
import com.imdb.entity.MovieEntity;
import com.imdb.service.ImdbService;

@RestController
public class ImdbController {

	@Autowired
	private ImdbService imdbService;

	@GetMapping("/get-movie")
	public List<Movie> getList() throws IOException {
		return imdbService.getJsopData();
	}

	@GetMapping("/get-movie/{castName}")
	public List<MovieEntity> getMoviesByCastName(@PathVariable String castName) {
		return imdbService.getMoviesByCastName(castName);

	}

	@GetMapping("/get-movie/{castName}/{topN}")
	public List<MovieEntity> getTopNmoviesByCastName(@PathVariable String castName, @PathVariable int topN) {
		return imdbService.getTopNmoviesByCastName(castName, topN);
	}
}
