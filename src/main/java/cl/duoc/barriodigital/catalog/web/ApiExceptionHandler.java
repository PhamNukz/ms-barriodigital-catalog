package cl.duoc.barriodigital.catalog.web;

import cl.duoc.barriodigital.catalog.service.NombreDuplicadoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    ProblemDetail noEncontrado(NoSuchElementException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(NombreDuplicadoException.class)
    ProblemDetail nombreDuplicado(NombreDuplicadoException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
    }

    /**
     * Red de seguridad para cualquier violacion de constraint que no se haya
     * chequeado antes (p. ej. dos inserts simultaneos con el mismo nombre pasan
     * el existsBy... y colisionan en el INSERT). Sin esto salia como 500 pelado:
     * un dato invalido del usuario no es un error interno del servidor.
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    ProblemDetail datoDuplicado(DataIntegrityViolationException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT,
                "El dato entra en conflicto con uno existente (el nombre del tipo de trámite debe ser único).");
    }
}
