package es.ies.puerto.exception;

/**
 * @author danielrguezh
 * @version 1.0.0
 */
public class RepasoException extends Exception{
    public RepasoException(String mensaje){
        super(mensaje);
    }

    public RepasoException(String mensaje, Throwable tipo){
        super(mensaje, tipo);
    }
}
