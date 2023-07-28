package edu.mjia2.emich.movieCheckoutSystem4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieApiController {
    private final MovieService movieService;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private CheckoutRepository checkoutRepository;

    public MovieApiController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movie")
    public ResponseEntity<Movie> getMovieByTitle(@RequestParam("title") String title) {
        Movie movie = movieService.findByTitle(title);
        if (movie == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @GetMapping("/checkoutHistory")
    public List<Checkout> getCheckoutHistoryByCustomerId(@RequestParam int customerId) {
        return checkoutRepository.findByCustomer_Id(customerId);
    }
}
