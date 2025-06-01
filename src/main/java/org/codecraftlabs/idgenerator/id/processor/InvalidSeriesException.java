package org.codecraftlabs.idgenerator.id.processor;

public class InvalidSeriesException extends RuntimeException {
  public InvalidSeriesException(String message) {
    super(message);
  }

  public InvalidSeriesException(String message, Throwable exception) {
    super(message, exception);
  }
}
