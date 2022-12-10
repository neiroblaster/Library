package com.shchayuk.library.SpringBoot.models;


import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Size(min = 4, max = 20, message = "The length of the username should be from 4 to 20 characters")
    @Column(name = "username")
    private String username;

    @Min(value = 1923, message = "Enter the year greater than 1923")
    @Max(value = 2017, message = "Enter the year less than 2017")
    @Column(name = "year_of_the_birth")
    private int yearOfTheBirth;

    @Column(name = "password")
    private String password;

    public Client() {
    }

    public Client(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getYearOfTheBirth() {
        return yearOfTheBirth;
    }

    public void setYearOfTheBirth(int yearOfTheBirth) {
        this.yearOfTheBirth = yearOfTheBirth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", yearOfTheBirth=" + yearOfTheBirth +
                ", password='" + password + '\'' +
                '}';
    }
}


