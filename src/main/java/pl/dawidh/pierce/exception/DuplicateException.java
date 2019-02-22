package pl.dawidh.pierce.exception;

public class DuplicateException extends RuntimeException {
    public DuplicateException(String error){
        super(error);
    }

    public DuplicateException(){

    }
}
