package com.taller.ms_pedidos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientResponseException;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CantidadExcedeLimiteException.class)
    public ResponseEntity<ApiError> handleCantidadExcedeLimite(CantidadExcedeLimiteException ex) {
        ApiError error = new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                ex.getMessage(),
                null
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(LimitePedidosActivosException.class)
    public ResponseEntity<ApiError> handleLimitePedidosActivos(LimitePedidosActivosException ex) {
        ApiError error = new ApiError(
                HttpStatus.CONFLICT.value(),
                "Conflict",
                ex.getMessage(),
                null
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(PedidoYaCanceladoException.class)
    public ResponseEntity<ApiError> handlePedidoYaCancelado(PedidoYaCanceladoException ex) {
        ApiError error = new ApiError(
                HttpStatus.CONFLICT.value(),
                "Conflict",
                ex.getMessage(),
                null
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(PedidoNotFoundException.class)
    public ResponseEntity<ApiError> handlePedidoNotFound(PedidoNotFoundException ex) {
        ApiError error = new ApiError(
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                ex.getMessage(),
                null
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    //error que devuelve otro microservicio (404, 409, etc) -> se propaga el mismo codigo
    @ExceptionHandler(RestClientResponseException.class)
    public ResponseEntity<ApiError> handleErrorDeOtroMicroservicio(RestClientResponseException ex) {
        ApiError error = new ApiError(
                ex.getStatusCode().value(),
                "Error de otro microservicio",
                ex.getMessage(),
                null
        );
        return ResponseEntity.status(ex.getStatusCode()).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex) {

        List<String> listError
                = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .toList();

        ApiError error = new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                "Uno o más campos no son válidos",
                listError
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    //Any other error
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception ex) {
        ApiError error = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                "Sucedió un error inesperado" + ex.getMessage(),
                null
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

}