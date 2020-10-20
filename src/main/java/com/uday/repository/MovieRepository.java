package com.uday.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uday.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

}
