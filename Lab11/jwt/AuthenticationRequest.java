package com.example.lab11.jwt;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * A class for handling the authentication request.
 */
@ApiModel(description = "A request for authentication")
public class AuthenticationRequest {

    @ApiModelProperty(notes = "The username of the user which started the request")
    private String username;

    @ApiModelProperty(notes = "The password of the user which started the request")
    private String password;

    /**
     * Creates an empty request.
     */
    public AuthenticationRequest() {
    }

    /**
     * Creates a request with the user's username and password.
     *
     * @param username the user's username.
     * @param password the user's password.
     */
    public AuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Retrieves the username from the request.
     *
     * @return the retrieved username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the request's username.
     *
     * @param username the new value of the request's username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the password from the request.
     *
     * @return the retrieved password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the request's password.
     *
     * @param password the new value of the request's password.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
