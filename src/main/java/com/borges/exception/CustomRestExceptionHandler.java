package com.borges.exception;

import com.borges.dtos.ApiErrorDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler( { LojaException.class } )
    public ResponseEntity<ApiErrorDto> handleLojaException(LojaException e, WebRequest webRequest) {
        String error = "Erro no sistema.";
        ApiErrorDto apiError = new ApiErrorDto(e.getMsg(), error, HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<ApiErrorDto>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler( { EntityNotFoundException.class } )
    public ResponseEntity<ApiErrorDto> handleEntityNotFoundsException(EntityNotFoundException e, WebRequest webRequest) {
        String error = "Recurso não encontrada.";
        ApiErrorDto apiError = new ApiErrorDto(e.getMsg(), error, HttpStatus.NOT_FOUND);
        return new ResponseEntity<ApiErrorDto>(apiError, new HttpHeaders(), apiError.getStatus());
    }
}
