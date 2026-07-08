package mx.edu.utez.JuventudxTemixco.Config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. Captura cuando no se encuentra un recurso (como tu "Section not found")
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<CustomResponse> handleRuntimeException(RuntimeException ex) {
        CustomResponse response = new CustomResponse(true, HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // 2. Captura los errores de lógica de negocio (como el IllegalStateException de tu delete)
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<CustomResponse> handleIllegalState(IllegalStateException ex) {
        CustomResponse response = new CustomResponse(true, HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // 3. Captura cualquier otro error inesperado del sistema
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomResponse> handleAllExceptions(Exception ex) {
        CustomResponse response = new CustomResponse(true, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Ocurrió un error inesperado en el servidor");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        Map<String, Object> response = new HashMap<>();

        response.put("error", true);
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("errors", errors);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}