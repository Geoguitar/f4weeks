package br.com.f4weeks.repository;

import br.com.f4weeks.model.InteracaoVersiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InteracaoVersiculoRepository extends JpaRepository<InteracaoVersiculo, Long> {

    // Busca a interação específica de um usuário com um versículo (para verificar se já está lido/colorido)
    Optional<InteracaoVersiculo> findByUsuarioIdAndVersiculoId(Long usuarioId, Long versiculoId);

    // Conta quantos versículos o usuário marcou como lidos no total
    long countByUsuarioIdAndLidoTrue(Long usuarioId);

    // Conta quantos versículos o usuário leu dentro de uma Parashá específica
    long countByUsuarioIdAndVersiculoNomeParashaAndLidoTrue(Long usuarioId, String nomeParasha);
}