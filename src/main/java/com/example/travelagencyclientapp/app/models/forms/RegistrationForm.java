package com.example.travelagencyclientapp.app.models.forms;

import com.example.travelagencyclientapp.app.validators.EqualFields;

import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.Objects;

@EqualFields(baseField = "password",matchField = "confirmPassword")
public class RegistrationForm {

    private Integer id;

    @Pattern(regexp = "[A-Za-z0-9]{3,30}", message = "Invalid username")
    private String username;

    @Pattern(regexp = "[A-Za-z]{3,30}",message = "Invalid name")
    private String name;

    @Pattern(regexp = "[A-Za-z]{3,30}",message = "Invalid surname")
    private String surname;

    @Pattern(regexp="(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])",
            message = "Invalid email")
    private String email;

    @Pattern(regexp = "[A-Za-z0-9]{3,30}",message = "Invalid password")
    private String password;

    @Pattern(regexp = "[A-Za-z0-9]{3,30}",message = "Invalid password")
    private String confirmPassword;

    private LocalDateTime time;

    public RegistrationForm() {

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistrationForm registrationForm = (RegistrationForm) o;
        return Objects.equals(id, registrationForm.id) &&
                Objects.equals(name, registrationForm.name) &&
                Objects.equals(surname, registrationForm.surname) &&
                Objects.equals(email, registrationForm.email) &&
                Objects.equals(password, registrationForm.password) &&
                Objects.equals(time, registrationForm.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, password, time);
    }
}
