package me.deepesh.springyelpcamp.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class YelpCampException extends RuntimeException implements EnrichedException {

  public YelpCampException() {

    super();
  }

  public YelpCampException(String message) {

    super(message);
  }

  public YelpCampException(String message, Throwable cause) {

    super(message, cause);
  }

  public YelpCampException(Throwable cause) {

    super(cause);
  }

  protected YelpCampException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {

    super(message, cause, enableSuppression, writableStackTrace);
  }

  @Override
  public String getErrorMessage() {

    return "Yelpcamp Exception";
  }

  @Override
  public HttpStatus getHttpStatus() {

    return HttpStatus.INTERNAL_SERVER_ERROR;
  }
}
