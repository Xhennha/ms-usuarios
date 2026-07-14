package cl.aduanaenlinea.msusuarios.dto;

import cl.aduanaenlinea.msusuarios.model.Usuario;

import java.time.LocalDateTime;

/**
 * Datos que devolvemos al frontend.
 * Ojo: a proposito NO incluye la password, para no exponerla en la respuesta del API.
 */
public class UsuarioResponse {

    private Long id;
    private String name;
    private String email;
    private LocalDateTime createdAt;

    public UsuarioResponse(Usuario usuario) {
        this.id = usuario.getId();
        this.name = usuario.getName();
        this.email = usuario.getEmail();
        this.createdAt = usuario.getCreatedAt();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
