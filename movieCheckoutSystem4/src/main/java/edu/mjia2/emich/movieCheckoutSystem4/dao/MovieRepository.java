package edu.mjia2.emich.movieCheckoutSystem4.dao;

import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    Optional<Movie> findByTitle(String title);

    @Query("SELECT m FROM Movie m WHERE (:searchBy = 'title' AND LOWER(m.title) LIKE LOWER(CONCAT('%', :keyword, '%'))) OR (:searchBy = 'description' AND LOWER(m.description) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    List<Movie> searchMovies(@Param("searchBy") String searchBy, @Param("keyword") String keyword);

}
