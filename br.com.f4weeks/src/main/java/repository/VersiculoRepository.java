package repository;

import model.Versiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VersiculoRepository extends JpaRepository<Versiculo, Long> {

    // Busca todos os versículos de um capítulo específico ordenados pelo número
    List<Versiculo> findByLivroAndCapituloOrderByVersiculoAsc(String livro, Integer capitulo);

    // Busca todos os versículos pertencentes a uma Parashá específica
    List<Versiculo> findByNomeParashaOrderByLivroAscCapituloAscVersiculoAsc(String nomeParasha);
}