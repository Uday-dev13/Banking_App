package Allu.Banking_App.Exception;

public class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException(String message){
        super(message);
    }
}
