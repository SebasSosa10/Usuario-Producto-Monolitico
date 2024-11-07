package com.UsuarioProducto.Usuario_productos.dto;


import jakarta.validation.constraints.*;

public class DtoUser {
    @NotBlank(message = "You must have a valid national identification document.")
    @Size(min = 4, message = "National ID must have at least 4 characters.")
    private String nationalId;
    @NotBlank(message = "Must have a name")
    private String firstName;
    @NotBlank(message = "Must have a last name")
    private String lastName;
    @NotBlank(message = "You must have an address")
    private String address;
    @NotBlank(message = "You must have a password")
    private String password;
    @NotBlank(message = "You must have an email")
    @Email(message = "notiene@gmail.com")
    private String email;
    @NotBlank(message = "You must have a username")
    private String username;

    public DtoUser() {
        super();
    }

    public DtoUser(String nationalId, String firstName, String lastName, String address, String password, String email, String username) {
        this.nationalId = nationalId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.password = password;
        this.email = email;
        this.username = username;
    }
    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}

