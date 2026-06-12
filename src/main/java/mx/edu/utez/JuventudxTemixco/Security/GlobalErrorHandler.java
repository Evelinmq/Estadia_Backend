package mx.edu.utez.JuventudxTemixco.Security;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalErrorHandler {
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentials(BadCredentialsException ex)
    {
        return new ResponseEntity<>("Usuario o contraseña incorrectos",
                HttpStatus.UNAUTHORIZED);

    }
    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<String> handleDisabled(DisabledException ex) {
        return new ResponseEntity<>("Cuenta deshabilitada", HttpStatus.FORBIDDEN);
    }
}
