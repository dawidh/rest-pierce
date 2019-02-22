package pl.dawidh.pierce.exception;

public class NoParametersException extends RuntimeException {
    public NoParametersException(String error){
        super(error);
    }

    public NoParametersException(){

    }
}
