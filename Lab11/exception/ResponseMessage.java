package com.example.lab11.exception;

/**
 * An implementation of an error messages.
 */
public class ResponseMessage {
    private String message;

    /**
     * Creates a new error message.
     *
     * @param msg the error's message.
     */
    public ResponseMessage(String msg) {
        this.message = msg;
    }

    /**
     * Returns the error's message.
     *
     * @return a <code>String</code>
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets a new message for the error.
     *
     * @param message the error's new message.
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
