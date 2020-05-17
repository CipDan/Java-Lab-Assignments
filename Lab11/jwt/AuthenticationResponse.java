package com.example.lab11.jwt;

/**
 * A class for handling the authentication procedure's response.
 */
public class AuthenticationResponse {

    private final String jwt;

    /**
     * Creates a response for the authentication procedure.
     *
     * @param jwt the Json Web Token associated with the response.
     */
    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    /**
     * Obtains the Json Web Token.
     *
     * @return a <code>String</code>.
     */
    public String getJwt() {
        return jwt;
    }
}
