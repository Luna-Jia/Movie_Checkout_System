package edu.mjia2.emich.movieCheckoutSystem4.dao;

import java.util.List;

public interface CheckoutRepository extends JpaRepository<Checkout, Integer> {

    List<Checkout> findByCustomerIdAndMovieId(int customerId, int movieId);

    List<Checkout> findByCustomerId(int customerId);

    long countByMovieId(int movieId);

    List<Checkout> findByCustomer_Id(int customerId);
}
