package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Essencial para o Spring Security / Login (buscar usuário pelo e-mail)
    Optional<Usuario> findByEmail(String email);

    // Validação para evitar cadastros duplicados com o mesmo e-mail
    boolean existsByEmail(String email);
}