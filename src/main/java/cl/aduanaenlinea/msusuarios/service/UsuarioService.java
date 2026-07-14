package cl.aduanaenlinea.msusuarios.service;

import cl.aduanaenlinea.msusuarios.dto.LoginRequest;
import cl.aduanaenlinea.msusuarios.dto.RegistroRequest;
import cl.aduanaenlinea.msusuarios.model.Usuario;
import cl.aduanaenlinea.msusuarios.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

/**
 * Logica de negocio de usuarios: registro y login.
 * El Controller solo recibe la peticion HTTP y delega aqui.
 */
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    // Spring inyecta el repositorio automaticamente por el constructor
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario registrar(RegistroRequest datos) {
        if (usuarioRepository.existsByEmail(datos.getEmail())) {
            throw new NegocioException("Ya existe una cuenta registrada con ese correo");
        }

        Usuario nuevoUsuario = new Usuario(datos.getName(), datos.getEmail(), datos.getPassword());
        return usuarioRepository.save(nuevoUsuario);
    }

    public Usuario login(LoginRequest datos) {
        Usuario usuario = usuarioRepository.findByEmail(datos.getEmail())
                .orElseThrow(() -> new NegocioException("Correo o contrasena incorrectos"));

        // Comparacion simple de texto plano: suficiente para el alcance de este prototipo academico.
        // En un sistema real la password se guarda con hash (BCrypt) y se compara con passwordEncoder.matches().
        if (!usuario.getPassword().equals(datos.getPassword())) {
            throw new NegocioException("Correo o contrasena incorrectos");
        }

        return usuario;
    }
}
