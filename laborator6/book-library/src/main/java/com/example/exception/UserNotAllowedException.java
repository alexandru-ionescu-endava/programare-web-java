package com.example.exception;

public class UserNotAllowedException
        extends RuntimeException {

    public UserNotAllowedException() {
        super("User not allowed to make this action");
    }
}
