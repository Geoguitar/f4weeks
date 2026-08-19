package controller;

import model.Versiculo;
import repository.VersiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pentateuco")
public class PentateucoController {

    @Autowired
    private VersiculoRepository repository;

    @GetMapping("/parashot/{nome}/versiculos")
    public ResponseEntity<List<Versiculo>> getParasha(@PathVariable String nome) {
        List<Versiculo> trecho = repository.findByNomeParashaOrderByLivroAscCapituloAscVersiculoAsc(nome);

        if (trecho == null || trecho.isEmpty()) {
            // Forma explícita que o compilador entende sem erros
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Forma explícita que o compilador entende sem erros
        return new ResponseEntity<>(trecho, HttpStatus.OK);
    }
}
//
//    @GetMapping("/parashot/{nome}/versiculos")
//    public ResponseEntity<List<Versiculo>> getParasha(@PathVariable String nome) {
//        List<Versiculo> trecho = repository.findByNomeParashaOrderByLivroAscCapituloAscVersiculoAsc(nome);
//        if(trecho.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(trecho);
//    }
//}