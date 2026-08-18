package controller;

import entity.Versiculo;
import repository.VersiculoRepository;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pentateuco")
public class PentateucoController {

    @Autowired
    private VersiculoRepository repository;

    @GetMapping("/parashot/{nome}/versiculos")
    public ResponseEntity<List<Versiculo>> getParasha(@PathVariable String nome) {
        List<Versiculo> trecho = repository.findByNomeParashaOrderByLivroAscCapituloAscVersiculoAsc(nome);
        if(trecho.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(trecho);
    }
}