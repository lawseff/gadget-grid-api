package io.github.lawseff.gadgets.web;

import io.github.lawseff.gadgets.persistence.exception.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GadgetExceptionHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<Object> handleResourceNotFoundException(EntityNotFoundException e) {
    return ResponseEntity.notFound().build();
  }

}
