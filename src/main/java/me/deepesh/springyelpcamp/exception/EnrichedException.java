package me.deepesh.springyelpcamp.exception;

import org.springframework.http.HttpStatus;

public interface EnrichedException {

  String getErrorMessage();

  HttpStatus getHttpStatus();
}
