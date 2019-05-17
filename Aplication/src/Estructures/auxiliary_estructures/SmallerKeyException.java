package Estructures.auxiliary_estructures;

public class SmallerKeyException extends Exception {

    public SmallerKeyException(){
        super("El nuevo valor es más pequeño que el actual.");
    }

}
