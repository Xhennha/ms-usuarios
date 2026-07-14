package cl.aduanaenlinea.msusuarios.controller;

import cl.aduanaenlinea.msusuarios.service.NegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Captura las excepciones lanzadas en los controllers y las transforma
 * en una respuesta JSON clara, en vez de un error 500 generico.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Errores de reglas de negocio (ej: correo ya registrado)
    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Map<String, String>> manejarNegocioException(NegocioException ex) {
        Map<String, String> cuerpo = new HashMap<>();
        cuerpo.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(cuerpo);
    }

    // Errores de validacion de los DTO (@NotBlank, @Email, etc.)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> manejarValidacion(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(campo ->
                errores.put(campo.getField(), campo.getDefaultMessage())
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
    }
}
