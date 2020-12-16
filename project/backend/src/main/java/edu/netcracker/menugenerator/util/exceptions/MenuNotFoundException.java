package edu.netcracker.menugenerator.util.exceptions;

public class MenuNotFoundException extends RuntimeException{
    public MenuNotFoundException(String message){
        super(message);
    }
}