package me.deepesh.springyelpcamp.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends YelpCampException {

  private final String name = "Not Found Exception";

  public NotFoundException() {

    super();
  }

  public NotFoundException(String message) {

    super(message);
  }

  public NotFoundException(String message, Throwable cause) {

    super(message, cause);
  }

  public NotFoundException(Throwable cause) {

    super(cause);
  }

  protected NotFoundException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {

    super(message, cause, enableSuppression, writableStackTrace);
  }

  @Override
  public String getErrorMessage() {

    return "Not Found";
  }

  @Override
  public HttpStatus getHttpStatus() {

    return HttpStatus.NOT_FOUND;
  }
}
