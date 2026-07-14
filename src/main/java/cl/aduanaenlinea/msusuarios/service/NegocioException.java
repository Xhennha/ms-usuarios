package cl.aduanaenlinea.msusuarios.service;

/**
 * Excepcion simple para errores de reglas de negocio
 * (ej: correo ya registrado, credenciales incorrectas).
 * Se separa de las excepciones tecnicas para poder responder
 * con un mensaje claro al frontend.
 */
public class NegocioException extends RuntimeException {

    public NegocioException(String mensaje) {
        super(mensaje);
    }
}
