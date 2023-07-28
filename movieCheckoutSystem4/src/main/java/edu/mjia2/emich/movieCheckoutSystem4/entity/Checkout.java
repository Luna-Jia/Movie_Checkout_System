package edu.mjia2.emich.movieCheckoutSystem4.entity;

import org.springframework.data.annotation.Id;

import java.util.Date;

@Entity
@Table(name="checkout_list")
public class Checkout {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "customer_id")
    private Customer customer;



    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "movie_id")
    private Movie movie;



    @Column(name = "checkout_date")
    private Date checkoutDate;


    @Column(name = "returned_date")
    private Date returnedDate;

    public Checkout() {
    }

    public Checkout(Customer customer, Movie movie, Date checkoutDate, Date returnedDate) {
        this.customer = customer;
        this.movie = movie;
        this.checkoutDate = checkoutDate;
        this.returnedDate = returnedDate;
    }

    public Checkout(int id, Customer customer, Movie movie, Date checkoutDate, Date returnedDate) {
        this.id = id;
        this.customer = customer;
        this.movie = movie;
        this.checkoutDate = checkoutDate;
        this.returnedDate = returnedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public Date getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(Date returnedDate) {
        this.returnedDate = returnedDate;
    }

    @Override
    public String toString() {
        return "Checkout{" +
                "id=" + id +
                ", customer=" + customer +
                ", movie=" + movie +
                ", checkoutDate=" + checkoutDate +
                ", returnedDate=" + returnedDate +
                '}';
    }
}
