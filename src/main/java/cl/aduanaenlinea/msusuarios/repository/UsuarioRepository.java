package cl.aduanaenlinea.msusuarios.repository;

import cl.aduanaenlinea.msusuarios.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositorio de acceso a datos para la entidad Usuario.
 * Spring Data JPA implementa automaticamente estos metodos en tiempo de ejecucion,
 * solo hay que declarar la firma siguiendo su convencion de nombres.
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);
}
