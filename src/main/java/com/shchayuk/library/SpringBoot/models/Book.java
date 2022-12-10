package com.shchayuk.library.SpringBoot.models;


import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "Book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotEmpty(message = "Enter the name of the book")
    @Size(min = 2, max = 100, message = "Enter the name of the book form 2 to 100 characters")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Enter the name of the book")
    @Size(min = 2, max = 100, message = "Enter the name of the book form 2 to 100 characters")
    @Column(name = "author")
    private String author;

    @Min(value = 1800, message = "The year should be greater than 1800")
    @Max(value = 2022, message = "The year should be less than 2022")
    @Column(name = "year")
    private int year;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;

    @Column(name = "lent_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lentDate;

    @Transient
    private Boolean isOver = false;

    public Book() {
    }

    public Book(String name, String author, int year) {
        this.name = name;
        this.author = author;
        this.year = year;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Date getLentDate() {
        return lentDate;
    }

    public void setLentDate(Date lentDate) {
        this.lentDate = lentDate;
    }

    public Boolean getOver() {
        return isOver;
    }

    public void setOver(Boolean over) {
        isOver = over;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", lentDate=" + lentDate +
                ", isOver=" + isOver +
                '}';
    }
}
