package pl.dawidh.pierce.exception;

public class NotFoundException extends RuntimeException {
    public  NotFoundException(String error){
        super(error);
    }

    public  NotFoundException(){

    }
}
