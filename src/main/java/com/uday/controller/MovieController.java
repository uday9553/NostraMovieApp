package com.uday.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.uday.model.Movie;
import com.uday.service.MovieService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value="Movie Controller", description="This is controller for my Nostra Movie App")
public class MovieController {

private MovieService movieService;
	
	@Autowired
	public MovieController(MovieService movieService) {
		this.movieService=movieService;
	}
	
	@ApiOperation(value = "View list of  Movies", response = Movie.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved "),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	        }
	)
	@GetMapping(value="/getAllMovies")
	public ResponseEntity<List<Movie>> getMovies(){
		return movieService.fetchAll();
	}
	
	@ApiOperation(value = "View Movies Names", response = String.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved "),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	        }
	)
	@GetMapping(value="/getMovieNames")
	public ResponseEntity<List<String>> getMovieNames(){
		return movieService.fetchMovieNames();
	}
	
	@ApiOperation(value = "Get a Movie By its ID", response = Movie.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved "),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	        }
	)
	@GetMapping("/getMovieById/{id}")
	public ResponseEntity<?> getMovieById(@PathVariable("id") Long id){		
		return movieService.getMovieById(id);
	}
	
	
	@ApiOperation(value = "save a  Movie", response = Movie.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved "),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	        }
	)
	@PostMapping("/saveMovie")
	public ResponseEntity<?> saveEmploy(@RequestBody @Valid Movie movie){
		return movieService.saveEmployee(movie);
	}
	
	@ApiOperation(value = "update a  Movie", response = Movie.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved "),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	        }
	)
	@PutMapping("/updateMovie")
	public ResponseEntity<?> updateEmploy(@RequestBody @Valid Movie movie){
		return movieService.updateEmployee(movie);
	}
	
	@ApiOperation(value = "delete a  Movie", response = Movie.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved "),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	        }
	)
	@DeleteMapping("/deleteByID/{id}")
	public ResponseEntity<?> deleteMovie(@PathVariable("id") Long id){		
		return movieService.deleteMovie(id);
	}
	
}
