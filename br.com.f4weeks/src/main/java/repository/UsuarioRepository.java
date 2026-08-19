package repository;

import model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Essencial para o Spring Security / Login (buscar usuário pelo e-mail)
    Optional<Usuario> findByEmail(String email);

    // Validação para evitar cadastros duplicados com o mesmo e-mail
    boolean existsByEmail(String email);
}