package edu.netcracker.menugenerator.util.exceptions;

public class PreferenceNotFoundException extends RuntimeException{
    public PreferenceNotFoundException(String message){
        super(message);
    }
}