package me.deepesh.springyelpcamp.handler;

import lombok.extern.slf4j.Slf4j;
import me.deepesh.springyelpcamp.exception.YelpCampException;
import me.deepesh.springyelpcamp.payload.ErrorResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class CentralExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(YelpCampException.class)
  public ResponseEntity<ErrorResponse> handleYelpcampException(
      YelpCampException ex, WebRequest request) {

    final ErrorResponse errorResponse =
        getErrorResponse(
            Collections.singletonList(ex.getMessage()),
            ex.getHttpStatus(),
            request,
            ex.getErrorMessage());

    return ResponseEntity.status(ex.getHttpStatus()).body(errorResponse);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {

    final List<String> errors =
        ex.getBindingResult().getFieldErrors().stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .collect(Collectors.toList());

    final ErrorResponse errorResponse =
        getErrorResponse(errors, status, request, "Method Argument Not Valid");

    return new ResponseEntity<>(errorResponse, headers, status);
  }

  @Override
  protected ResponseEntity<Object> handleExceptionInternal(
      Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

    final ErrorResponse errorResponse =
        getErrorResponse(
            Collections.singletonList(ex.getMessage()), status, request, ex.getMessage());

    return new ResponseEntity<>(errorResponse, headers, status);
  }

  private ErrorResponse getErrorResponse(
      List<String> userMessages, HttpStatus status, WebRequest request, String errorMessage) {

    return ErrorResponse.builder()
        .timeStamp(LocalDateTime.now())
        .errorCode(status.value())
        .errorMessage(errorMessage)
        .userMessages(userMessages)
        .path(request.getDescription(false).substring(4))
        .build();
  }
}
