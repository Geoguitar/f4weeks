package br.com.f4weeks.repository;

import java.util.List;

import entity.Versiculo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import repository.VersiculoRepository;

import static org.testcontainers.shaded.org.hamcrest.MatcherAssert.assertThat;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class VersiculoRepositoryTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgresSQLContainer<>("postgres:15-alpine");

    @Autowiredv
    private VersiculoRepository repository;

    @Test
    @DisplayName("Deve salvar um versúculo e buscar pela Parasha com sucesso!")
    void  deveSalvarEBuscarPorParasha(){

        Versiculo v = new Versiculo();
        v.setLivro("Gênesis");
        v.setCapitulo(1);
        v.setVersiculo(1);
        v.setTexto("No princípio criou O Eterno os céus e a terra.");
        v.setNomeParasha("Bereshit");

        repository.save(v);

        List<Versiculo> resultado = repository.findByNomeParashaOrderByLivroAscCapituloAscVersiculoAsc("Bereshit");

        assertThat(resultado).isNotEmpty();
        assertThat(resultado.get(0).getTexto()).isEqualTo("No princípio criou O Eterno os céus e a terra.");
    }

}
