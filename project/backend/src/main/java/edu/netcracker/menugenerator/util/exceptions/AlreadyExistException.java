package edu.netcracker.menugenerator.util.exceptions;

import java.util.function.Supplier;

public class AlreadyExistException extends RuntimeException{
    public AlreadyExistException(String message){
        super(message);
    }
}
