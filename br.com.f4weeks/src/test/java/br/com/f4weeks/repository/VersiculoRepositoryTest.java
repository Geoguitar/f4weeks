package br.com.f4weeks.repository;

import java.util.List;

import br.com.f4weeks.model.Versiculo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
//@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class VersiculoRepositoryTest {

    // FORÇA O TESTCONTAINERS A USAR O SOCKET PADRÃO DIRETAMENTE NA JVM
//    static {
//        System.setProperty("docker.host", "unix:///var/run/docker.sock");
//    }

//    @Container
//    @ServiceConnection
//    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine");

    @Autowired
    private VersiculoRepository repository;

    @Test
    @DisplayName("Deve salvar um versículo e buscar pela Parasha com sucesso!")
    void deveSalvarEBuscarPorParasha() {
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




//package br.com.f4weeks.repository;
//
//import java.util.List;
//
//import br.com.f4weeks.model.Versiculo;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
//import org.testcontainers.containers.PostgreSQLContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DataJpaTest
//@Testcontainers
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//public class VersiculoRepositoryTest {
//
//    // Bloco estático unificado para autodetectar e configurar o socket do Docker no Linux
//    static {
//        java.io.File socketPadrao = new java.io.File("/var/run/docker.sock");
//        String xdgRuntime = System.getenv("XDG_RUNTIME_DIR");
//        java.io.File socketRootless = new java.io.File(xdgRuntime != null ? xdgRuntime + "/docker.sock" : "/run/user/1000/docker.sock");
//
//        if (socketPadrao.exists()) {
//            System.setProperty("docker.host", "unix:///var/run/docker.sock");
//            System.out.println("-> Testcontainers conectado ao Docker padrão: /var/run/docker.sock");
//        } else if (socketRootless.exists()) {
//            System.setProperty("docker.host", "unix://" + socketRootless.getAbsolutePath());
//            System.out.println("-> Testcontainers conectado ao Docker Rootless: " + socketRootless.getAbsolutePath());
//        } else {
//            // Fallback forçado para o rootless padrão caso os arquivos não sejam mapeados direto pelo Java File API
//            System.setProperty("docker.host", "unix:///run/user/1000/docker.sock");
//            System.out.println("-> Aviso: Forçando conexão ao socket Rootless padrão: /run/user/1000/docker.sock");
//        }
//    }
//
//    @Container
//    @ServiceConnection
//    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine");
//
//    @Autowired
//    private VersiculoRepository repository;
//
//    @Test
//    @DisplayName("Deve salvar um versículo e buscar pela Parasha com sucesso!")
//    void deveSalvarEBuscarPorParasha() {
//        Versiculo v = new Versiculo();
//        v.setLivro("Gênesis");
//        v.setCapitulo(1);
//        v.setVersiculo(1);
//        v.setTexto("No princípio criou O Eterno os céus e a terra.");
//        v.setNomeParasha("Bereshit");
//
//        repository.save(v);
//
//        List<Versiculo> resultado = repository.findByNomeParashaOrderByLivroAscCapituloAscVersiculoAsc("Bereshit");
//
//        assertThat(resultado).isNotEmpty();
//        assertThat(resultado.get(0).getTexto()).isEqualTo("No princípio criou O Eterno os céus e a terra.");
//    }
//}