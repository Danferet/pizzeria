package Excepctions;

public class PrecioNoValidoException extends RuntimeException {
  public PrecioNoValidoException(String message) {
    super(message);
  }
}
