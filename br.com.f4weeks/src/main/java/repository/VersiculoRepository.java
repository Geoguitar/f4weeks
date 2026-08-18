package repository;

import com.github.dockerjava.api.model.Repository;
import entity.Versiculo;

import java.util.List;

@Repository
public interface VersiculoRepository extends JpaRepository<Versiculo, Long> {

    //Fará busca do capítulo inteiro
    List<Versiculo> findByLivroAndCapituloOrderByVersiculoAsc(String Livro, Integer capitulo);

    //Fará busca dos versículos de uma parasha específica
    List<Versiculo> findByNomeParashaOrderByLivroAscCapituloAscVersiculoAsc(String nomeParasha);
}
