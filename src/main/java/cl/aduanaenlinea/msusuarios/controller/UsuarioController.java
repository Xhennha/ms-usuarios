package cl.aduanaenlinea.msusuarios.controller;

import cl.aduanaenlinea.msusuarios.dto.LoginRequest;
import cl.aduanaenlinea.msusuarios.dto.RegistroRequest;
import cl.aduanaenlinea.msusuarios.dto.UsuarioResponse;
import cl.aduanaenlinea.msusuarios.model.Usuario;
import cl.aduanaenlinea.msusuarios.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Endpoints REST del microservicio de Usuarios.
 * Base URL: http://localhost:8081/api/usuarios
 * (CORS se configura de forma centralizada en config/CorsConfig.java)
 */
@RestController
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // GET http://localhost:8081/api/usuarios/health -> para probar rapido que el servicio esta arriba
    @GetMapping("/api/usuarios/health")
    public String health() {
        return "ms-usuarios funcionando correctamente";
    }

    // POST http://localhost:8081/api/usuarios/registro
    @PostMapping("/api/usuarios/registro")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponse registrar(@Valid @RequestBody RegistroRequest datos) {
        Usuario usuarioCreado = usuarioService.registrar(datos);
        return new UsuarioResponse(usuarioCreado);
    }

    // POST http://localhost:8081/api/usuarios/login
    @PostMapping("/api/usuarios/login")
    public UsuarioResponse login(@Valid @RequestBody LoginRequest datos) {
        Usuario usuario = usuarioService.login(datos);
        return new UsuarioResponse(usuario);
    }
}
