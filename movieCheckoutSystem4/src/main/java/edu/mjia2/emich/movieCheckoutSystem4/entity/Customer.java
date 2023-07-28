package edu.mjia2.emich.movieCheckoutSystem4.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "customer",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Checkout> checkoutList = new ArrayList<Checkout>();

    public void addCheckout(Checkout checkout) {
        checkoutList.add(checkout);
        checkout.setCustomer(this);
    }

    public Customer() {
    }

    public Customer(String name, List<Checkout> checkoutList) {
        this.name = name;
        this.checkoutList = checkoutList;
    }

    public Customer(int id, String name, List<Checkout> checkoutList) {
        this.id = id;
        this.name = name;
        this.checkoutList = checkoutList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Checkout> getCheckoutList() {
        return checkoutList;
    }

    public void setCheckoutList(List<Checkout> checkoutList) {
        this.checkoutList = checkoutList;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", checkoutList=" + checkoutList +
                '}';
    }
}
