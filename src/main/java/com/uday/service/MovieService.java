package com.uday.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.uday.exception.EntityNotFoundException;
import com.uday.model.Movie;
import com.uday.repository.MovieRepository;

@Service
public class MovieService {
	
private MovieRepository movieRepository;
	
	@Autowired
	public MovieService(MovieRepository movieRepository){
		this.movieRepository=movieRepository;		
	}
	public ResponseEntity<List<Movie>> fetchAll(){
		return ResponseEntity.ok(movieRepository.findAll());		
	}
	public ResponseEntity<List<String>> fetchMovieNames(){
		List<Movie> moviesList=movieRepository.findAll();
		List<String> movieNames=moviesList.stream().map(movie->movie.getMovieName()).collect(Collectors.toList());
		return ResponseEntity.ok(movieNames);
	}
	public ResponseEntity<?> getMovieById( Long id){
		Movie movie=movieRepository.findById(id).orElseThrow(()->
		new EntityNotFoundException("movie "+id+" not exist in db",HttpStatus.NOT_FOUND,new Throwable("Requested entity not found")));
		return ResponseEntity.ok(movie);		
	}
	
	public ResponseEntity<?> deleteMovie(Long id){
		Optional<Movie> deletedmovie=movieRepository.findById(id);
		if(deletedmovie.isPresent()) {
			movieRepository.deleteById(id);
		}else {
			throw new EntityNotFoundException("movie "+id+" not exist in db",HttpStatus.NOT_FOUND,new Throwable("Requested entity not found"));
		}		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	public ResponseEntity<?> saveEmployee(Movie movie){		
		Optional<Movie> savemovie=movieRepository.findById(movie.getMovieID());
		if(!savemovie.isPresent()) {
			movieRepository.save(movie);
		}else {
			throw new EntityNotFoundException("movie "+movie.getMovieID()+" exist in db",HttpStatus.FOUND,new Throwable("Requested entity already present"));
		}		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	public ResponseEntity<?> updateEmployee(Movie movie){		
		movieRepository.save(movie);		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
