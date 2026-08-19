package br.com.f4weeks.controller;

import br.com.f4weeks.model.Versiculo;
import br.com.f4weeks.repository.VersiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/versiculos")
public class VersiculoController {

    @Autowired
    private VersiculoRepository repository;

    // Endpoint para buscar todos os versículos
    @GetMapping
    public List<Versiculo> listarTodos() {
        return repository.findAll();
    }

    // Endpoint para buscar por Parashá
    @GetMapping("/parasha/{nomeParasha}")
    public List<Versiculo> buscarPorParasha(@PathVariable String nomeParasha) {
        return repository.findByNomeParashaOrderByLivroAscCapituloAscVersiculoAsc(nomeParasha);
    }

    // Endpoint para salvar um novo versículo
    @PostMapping
    public ResponseEntity<Versiculo> salvar(@RequestBody Versiculo versiculo) {
        Versiculo salvo = repository.save(versiculo);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }
}