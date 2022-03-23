package com.imdb.repositry;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.imdb.entity.MovieEntity;

@Repository
public class ImdbRepository {

	private Map<String, Set<MovieEntity>> movieTable;

	public ImdbRepository() {
		movieTable = new HashMap<>();
	}

	public void addMovie(String castName, MovieEntity movieEntity) {
		Set<MovieEntity> newMovie = movieTable.get(castName);
		if (newMovie == null) {
			newMovie = new TreeSet<>(Comparator.comparing(MovieEntity::getRating).reversed());
		}
		newMovie.add(movieEntity);
		movieTable.put(castName, newMovie);
	}

	public List<MovieEntity> getMoviesByCastName(String castName) {
		return new ArrayList<>(movieTable.get(castName));
	}

	public List<MovieEntity> getTopNmoviesByCastName(String castName, int topN) {
		return movieTable.get(castName).stream().limit(topN).collect(Collectors.toList());
	}

}
