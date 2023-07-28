package edu.mjia2.emich.movieCheckoutSystem4.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="year")
    private int year;

    @Column(name="length")
    private int length;

    @Column(name="genre")
    private String genre;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="release_date")
    private Date releaseDate;

    @Column(name="rating")
    private String rating;

    @Column(name = "numb_of_copies")
    private int numbOfCopies;

    public Movie() {
    }

    public Movie(String title, String description, int year, int length, String genre, Date releaseDate, String rating, int numbOfCopies) {
        this.title = title;
        this.description = description;
        this.year = year;
        this.length = length;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.numbOfCopies = numbOfCopies;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getNumbOfCopies() {
        return numbOfCopies;
    }

    public void setNumbOfCopies(int numbOfCopies) {
        this.numbOfCopies = numbOfCopies;
    }


    @JsonIgnore
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Checkout> checkoutList = new ArrayList<>();

    public List<Checkout> getCheckoutList() {
        return checkoutList;
    }

    public void setCheckoutList(List<Checkout> checkoutList) {
        this.checkoutList = checkoutList;
    }

    public void addCheckout(Checkout checkout) {
        checkoutList.add(checkout);
        checkout.setMovie(this);
    }

    public int getAvailableCopies() {
        int checkedOutCopies = (int) checkoutList.stream().filter(checkout -> checkout.getReturnedDate() == null).count();
        return numbOfCopies - checkedOutCopies;
    }



    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", year=" + year +
                ", length=" + length +
                ", genre='" + genre + '\'' +
                ", releaseDate=" + releaseDate +
                ", rating='" + rating + '\'' +
                ", numbOfCopies=" + numbOfCopies +
                ", checkoutList=" + checkoutList +
                '}';
    }
}
