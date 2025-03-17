package com.corenrn.gestaoprocessoetico.controller;

import com.corenrn.gestaoprocessoetico.domain.FasesProcesso;
import com.corenrn.gestaoprocessoetico.domain.ProcessoEtico;
import com.corenrn.gestaoprocessoetico.service.FasesProcessoService;
import com.corenrn.gestaoprocessoetico.service.ProcessoEticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/processo-etico")
@CrossOrigin(origins = "http://localhost:4200")
public class ProcessoEticoController {

    @Autowired
    private ProcessoEticoService processoEticoService;
    private final FasesProcessoService fasesProcessoService;

    public ProcessoEticoController(FasesProcessoService fasesProcessoService) {
        this.fasesProcessoService = fasesProcessoService;
    }
    @GetMapping("/{id}/fases")
    public ResponseEntity<List<FasesProcesso>> listarFasesDoProcesso(@PathVariable Long id) {
        List<FasesProcesso> fases = fasesProcessoService.findFasesByProcessoId(id);
        return ResponseEntity.ok(fases);
    }

    @PostMapping
    public ResponseEntity<ProcessoEtico> cadastroProcessoEtico(@RequestBody ProcessoEtico processoEtico) {
        ProcessoEtico novoProcessoEtico = processoEticoService.salvarProcessoEtico(processoEtico);
        return new ResponseEntity<>(novoProcessoEtico, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProcessoEtico>> listarProcessoEtico() {
        List<ProcessoEtico> lista = processoEticoService.findAllProcessoEtico();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProcessoEtico> buscarPorId(@PathVariable Long id) {
        ProcessoEtico processo = processoEticoService.findProcessoEticoById(id);
        return processo != null ? ResponseEntity.ok(processo) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProcessoEtico> atualizarProcessoEtico(@PathVariable Long id, @RequestBody ProcessoEtico processoAtualizado) {
        ProcessoEtico atualizado = processoEticoService.atualizarProcessoEtico(id, processoAtualizado);
        return atualizado != null ? ResponseEntity.ok(atualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProcessoEtico(@PathVariable Long id) {
        processoEticoService.deletarProcessoEtico(id);
        return ResponseEntity.noContent().build();
    }
}
