package edu.mjia2.emich.movieCheckoutSystem4.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class MainController {

    private Customer customer;
    @Autowired
    private MovieService movieService;

    @Autowired
    private CheckoutService checkoutService;

    @Autowired
    private CustomerService customerService;

    public MainController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/searchMovie")
    public String searchMovie(Model model, @RequestParam(defaultValue = "") String searchBy, @RequestParam(defaultValue = "") String val) {
        List<Movie> movieList = movieService.searchMovies(searchBy, val);

        model.addAttribute("movieList", movieList);
        if (customer != null) {
            model.addAttribute("customerId", customer.getId());
        } else {
            model.addAttribute("customerId", -1);
        }
        return "search-results";
    }


    @RequestMapping("/movieSearchInput")
    public String movieSearchInput() {
        return "search-movie";
    }

    @GetMapping("/list")
    public String listMovies(Model theModel, HttpSession session) {
        Integer loggedInCustomerId = (Integer) session.getAttribute("loggedInCustomerId");
        if (loggedInCustomerId == null) {
            return "redirect:/";
        }

        customer = customerService.findById(loggedInCustomerId); // Add this line
        List<Movie> theMovies = movieService.findAll();
        theModel.addAttribute("movieList", theMovies);
        theModel.addAttribute("customerId", loggedInCustomerId); // Add this line

        return "movie-list";
    }


    @GetMapping("/")
    public String home(Model model) {
        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        return "home";
    }


    @GetMapping("/login")
    public String login(@RequestParam("customerId") int customerId, HttpSession session) {
        session.setAttribute("loggedInCustomerId", customerId);
        return "redirect:/checkoutList";
    }

    @GetMapping("/checkoutList")
    public String showCheckoutList(Model theModel, HttpSession session) {
        Integer loggedInCustomerId = (Integer) session.getAttribute("loggedInCustomerId");
        if (loggedInCustomerId == null) {
            return "redirect:/";
        }

        Customer customer = customerService.findById(loggedInCustomerId);
        List<Checkout> checkoutList = checkoutService.findByCustomerId(loggedInCustomerId);

        theModel.addAttribute("customerName", customer.getName());
        theModel.addAttribute("checkoutList", checkoutList);

        return "checkout-list";
    }


    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        // create model attribute to bind form data
        Movie theMovie = new Movie();

        theModel.addAttribute("movie", theMovie);

        return "new-movie";
    }


    @PostMapping("/save")
    public String saveMovie(@ModelAttribute("movie") Movie theMovie) {
        // save the movie
        Movie newMovie = new Movie(theMovie.getTitle(), theMovie.getDescription(), theMovie.getYear(), theMovie.getLength(),
                theMovie.getGenre(), theMovie.getReleaseDate(), theMovie.getRating(), theMovie.getNumbOfCopies());
        movieService.save(newMovie);

        // use a redirect to prevent duplicate submissions
        return "redirect:/list";
    }


    @GetMapping("/delete")
    public String delete(@RequestParam("movieId") int id) {

        // delete the employee
        movieService.deleteById(id);

        // redirect to /employees/list
        return "movie-list";

    }


    @GetMapping("/checkout")
    public String checkoutMovie(@RequestParam("customerId") int customerId, @RequestParam("movieId") int movieId) {
        Customer customer = customerService.findById(customerId);
        Movie movie = movieService.findById(movieId);

        if (movie.getAvailableCopies() > 0) {
            Checkout checkout = new Checkout();
            checkout.setCustomer(customer);
            checkout.setMovie(movie);
            checkout.setCheckoutDate(new Date());

            checkoutService.saveCheckout(checkout);

            //movie.setAvailableCopies(movie.getAvailableCopies() - 1);
            movie.getAvailableCopies();
            movieService.save(movie);
        }

        return "redirect:/checkoutList?customerId=" + customerId;
    }



    @GetMapping("/return")
    public String returnMovie(@RequestParam("checkoutId") int checkoutId, HttpSession session) {
        Integer loggedInCustomerId = (Integer) session.getAttribute("loggedInCustomerId");
        if (loggedInCustomerId == null) {
            return "redirect:/";
        }

        Checkout checkout = checkoutService.findById(checkoutId);
        if (checkout != null) {
            checkout.setReturnedDate(new Date());
            checkoutService.saveCheckout(checkout);
        }

        return "redirect:/checkoutList";
    }



}
