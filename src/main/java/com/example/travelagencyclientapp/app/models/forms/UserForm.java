package com.example.travelagencyclientapp.app.models.forms;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;

public class UserForm {

    private Integer id;

    @Size(min = 3, max =  30)
    private String username;

    @Pattern(regexp = "[A-Za-z]+")
    @Size(min = 3, max =  30)
    private String name;

    @Pattern(regexp = "[A-Za-z]{3,30}")
    private String surname;

    @Email
    private String email;

    private String password;

    private LocalDateTime time;

    public UserForm() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserForm userForm = (UserForm) o;
        return Objects.equals(id, userForm.id) &&
                Objects.equals(name, userForm.name) &&
                Objects.equals(surname, userForm.surname) &&
                Objects.equals(email, userForm.email) &&
                Objects.equals(password, userForm.password) &&
                Objects.equals(time, userForm.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, password, time);
    }
}
