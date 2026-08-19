package controller;

import br.com.f4weeks.F4weeksApplication;
import br.com.f4weeks.controller.PentateucoController;
import br.com.f4weeks.model.Versiculo;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.ContextConfiguration;
import br.com.f4weeks.repository.VersiculoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean; // <-- NOVA ANOTAÇÃO DO SPRING BOOT 3.4
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PentateucoController.class)
@ContextConfiguration(classes = F4weeksApplication.class)
@AutoConfigureMockMvc(addFilters = false) // Desativa filtros de segurança para focar no Controller
class PentateucoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean // <-- SUBSTITUIU O ANTIGO @MockBean
    private VersiculoRepository repository;

    @Test
    @DisplayName("Deve retornar status 200 e a lista de versículos ao buscar por uma Parashá")
    void deveRetornarVersiculosPorParasha() throws Exception {

        Versiculo mockVersiculo = new Versiculo();
        mockVersiculo.setLivro("Gênesis");
        mockVersiculo.setCapitulo(1);
        mockVersiculo.setVersiculo(1);
        mockVersiculo.setTexto("No princípio...");
        mockVersiculo.setNomeParasha("Bereshit");

        Mockito.when(repository.findByNomeParashaOrderByLivroAscCapituloAscVersiculoAsc("Bereshit"))
                .thenReturn(List.of(mockVersiculo));

        mockMvc.perform(get("/api/v1/pentateuco/parashot/Bereshit/versiculos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].livro").value("Gênesis"))
                .andExpect(jsonPath("$[0].texto").value("No princípio..."));
    }
}