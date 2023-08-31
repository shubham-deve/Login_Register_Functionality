package com.Assignment.modal;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int id;
    @Transient
private String Role;
    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 4,message = "name should be greater then 4 charachter")
    private String username;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "your Email address is not valid")
    private  String email;

    @NotEmpty(message = "password Cannot be empty")
    @Size(min = 5,max = 10,message = "password must be under 5 -10  letter")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$")
    private String password;


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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(){};

    @Override
    public String toString() {
        return "user{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
