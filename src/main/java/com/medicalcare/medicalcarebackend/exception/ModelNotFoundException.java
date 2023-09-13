package com.medicalcare.medicalcarebackend.exception;


//@ResponseStatus(HttpStatus.NOT_FOUND) //este permite ajustar el codigo que retorna el error ; como ya se esta usando en ResponseExceptionHandler, es redundante tenerlo aqui
////se crea una excepcion customizada heredando de las clases de excepciones
public class ModelNotFoundException extends RuntimeException{

    public ModelNotFoundException (String message){
        super(message);
    }
}
