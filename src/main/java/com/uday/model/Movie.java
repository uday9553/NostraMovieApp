package com.uday.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Table(name="movie")
@Data
public class Movie {

	@ApiModelProperty(notes = "This attribute is for maintaing movieID")
	@Id
	@Column(name="movieid")
	private Long movieID;
	
	@ApiModelProperty(notes = "This attribute is for maintaing movie name and its mandatory")
	@Column(name="moviename")	
	@NotNull(message="movie name shouldnot be null/empty")
	private String movieName;
	

	@ApiModelProperty(notes = "This attribute is for maintaing movie released location and its not mandatory")
	@Column(name="location")
	private String location;
	
	@ApiModelProperty(notes = "This attribute is for maintaing movie released date and its not mandatory")
	@Column(name="date")
	private String date;
	
	@ApiModelProperty(notes = "This attribute is for maintaing movie rating location and its mandatory")
	@Column(name="rating")	
	@NotNull(message="movie name shouldnot be null/empty")
	@Min(value=1,message="should be in between 1 and 5")
	@Max(value=5,message="should be in between 1 and 5")
	private Integer rating;
	  
	public Long getMovieID() {
		return movieID;
	}

	public Movie() {
		super();
	}

	public void setMovieID(Long movieID) {
		this.movieID = movieID;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

}
