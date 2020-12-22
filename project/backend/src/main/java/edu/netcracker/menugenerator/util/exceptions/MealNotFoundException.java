package edu.netcracker.menugenerator.util.exceptions;

public class MealNotFoundException extends RuntimeException{
    public MealNotFoundException(String message){
        super(message);
    }
}