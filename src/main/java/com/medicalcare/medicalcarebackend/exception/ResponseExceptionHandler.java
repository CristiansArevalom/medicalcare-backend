package com.medicalcare.medicalcarebackend.exception;


import org.springframework.cglib.core.Local;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

/**
 * ESta clase con la anotacion RestCopntrollerAdvice permite interceptar
 * las excepciones
 */
@RestControllerAdvice // = @ControllerAdive + @ResponseBody
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorResponse> handleAllException(Exception ex, WebRequest requiest) {
        //Se instancia el error
        CustomErrorResponse err =  new CustomErrorResponse(LocalDateTime.now(),ex.getMessage(), requiest.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleModelNotFoundException(ModelNotFoundException ex, WebRequest request){
        CustomErrorResponse err = new CustomErrorResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    //Desde Spring Boot 3 , permite personalizar un mensaje de error sin necesidad de crear la clase CustomErrorResponse como plantilla para los mensajes
    /*@ExceptionHandler(ModelNotFoundException.class)
    public ProblemDetail handleModelNotFoundException(ModelNotFoundException ex, WebRequest request){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setTitle("Model Not Found Exception");
        problemDetail.setType(URI.create("/not-found"));
        problemDetail.setProperty("extra1", "extra-value");
        problemDetail.setProperty("extra2", 32);
        return problemDetail;
    }*/

    //Desde Spring Boot 3 , permite personalizar un mensaje de error sin necesidad de crear la clase CustomErrorResponse como plantilla para los mensajes
    /*@ExceptionHandler(ModelNotFoundException.class)
    public ErrorResponse handleModelNotFoundException(ModelNotFoundException ex, WebRequest request){
        return ErrorResponse.builder(ex, HttpStatus.NOT_FOUND, ex.getMessage())
                .title("Model Not Found")
                .type(URI.create(request.getContextPath()))
                .property("extra1", "extra-value")
                .property("extra2", 32)
                .build();
    }*/



    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String msg = ex.getBindingResult().getFieldErrors().stream().map(
                e -> e.getField().concat(":").concat(e.getDefaultMessage().concat(" "))
        ).collect(Collectors.joining());

        CustomErrorResponse err = new CustomErrorResponse(LocalDateTime.now(), msg, request.getDescription(false));
        return  new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
/**
 * Una buena practica es que el ex.getMessage() tenga un error que solo conozca el area de desarrollo y no retornar ese tipo de erro ya que puede ser
 * información sensible
 * requiest.getDescription(false) permite indicar la url en la que devolvio el error
 */


}
