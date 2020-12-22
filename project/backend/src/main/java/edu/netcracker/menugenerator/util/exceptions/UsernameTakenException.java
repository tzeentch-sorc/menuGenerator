package edu.netcracker.menugenerator.util.exceptions;

public class UsernameTakenException extends RuntimeException{
    public UsernameTakenException(String message){
        super(message);
    }
}