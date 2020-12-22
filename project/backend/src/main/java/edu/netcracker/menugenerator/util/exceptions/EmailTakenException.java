package edu.netcracker.menugenerator.util.exceptions;

public class EmailTakenException extends RuntimeException{
    public EmailTakenException(String message){
        super(message);
    }
}