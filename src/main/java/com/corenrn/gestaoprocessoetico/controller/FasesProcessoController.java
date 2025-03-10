package com.corenrn.gestaoprocessoetico.controller;

import com.corenrn.gestaoprocessoetico.domain.FasesProcesso;
import com.corenrn.gestaoprocessoetico.service.FasesProcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/fases-processo")
@CrossOrigin(origins = "http://localhost:4200")
public class FasesProcessoController {

    @Autowired
    private FasesProcessoService fasesProcessoService;

    @PostMapping
    public ResponseEntity<FasesProcesso> cadastroFaseProcesso(@RequestBody FasesProcesso fasesProcesso) {
        FasesProcesso novaFase = fasesProcessoService.saveFases(fasesProcesso);
        return new ResponseEntity<>(novaFase, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<FasesProcesso>> listarFasesProcesso() {
        List<FasesProcesso> lista = fasesProcessoService.findAllFases();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<FasesProcesso> buscarFaseProcessoPorId(@PathVariable("id") Long id) {
        Optional<FasesProcesso> faseProcesso = fasesProcessoService.findFasesProcessoById(id);
        return faseProcesso.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}")
    public ResponseEntity<FasesProcesso> atualizarFaseProcesso(@PathVariable("id") Long id,
                                                               @RequestBody FasesProcesso fasesProcesso) {
        FasesProcesso faseAtualizada = fasesProcessoService.updateFasesProcesso(id, fasesProcesso);
        if (faseAtualizada != null) {
            return new ResponseEntity<>(faseAtualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarFaseProcesso(@PathVariable("id") Long id) {
        fasesProcessoService.deleteFasesProcesso(id);
    }
}
