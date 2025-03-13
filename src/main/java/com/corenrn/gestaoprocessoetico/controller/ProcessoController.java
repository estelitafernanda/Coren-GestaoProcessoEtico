package com.corenrn.gestaoprocessoetico.controller;

import com.corenrn.gestaoprocessoetico.domain.Processo;
import com.corenrn.gestaoprocessoetico.service.ProcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/processo")
@CrossOrigin(origins = "http://localhost:4200")
public class ProcessoController {

    @Autowired
    private ProcessoService processoService;

    @PostMapping
    public ResponseEntity<Processo> cadastroProcesso(@RequestBody Processo processo) {
        Processo novoProcesso = processoService.salvarProcesso(processo);
        return new ResponseEntity<>(novoProcesso, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Processo>> listarProcessos() {
        List<Processo> lista = processoService.listaProcessos();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Processo> buscarProcesso(@PathVariable Long id) {
        Processo processo = processoService.buscarProcesso(id);
        return processo != null ? ResponseEntity.ok(processo) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Processo> atualizarProcesso(@PathVariable Long id, @RequestBody Processo processoAtualizado) {
        System.out.println("Recebido para atualização: " + processoAtualizado);
        System.out.println("ID recebido: " + id);
        Processo atualizado = processoService.atualizarProcesso(id, processoAtualizado);
        return atualizado != null ? ResponseEntity.ok(atualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProcesso(@PathVariable Long id) {
        processoService.excluirProcesso(id);
        return ResponseEntity.noContent().build();
    }
}
