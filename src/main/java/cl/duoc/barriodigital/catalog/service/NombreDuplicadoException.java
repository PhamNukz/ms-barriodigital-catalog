package cl.duoc.barriodigital.catalog.service;

/** El nombre del tipo de tramite es unique en BD; esto lo reporta como conflicto, no como error interno. */
public class NombreDuplicadoException extends RuntimeException {

    public NombreDuplicadoException(String nombre) {
        super("Ya existe un tipo de tramite con el nombre \"" + nombre + "\"");
    }
}
