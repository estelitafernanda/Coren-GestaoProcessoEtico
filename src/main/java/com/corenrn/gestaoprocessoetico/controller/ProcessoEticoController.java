package com.corenrn.gestaoprocessoetico.controller;

import com.corenrn.gestaoprocessoetico.domain.ProcessoEtico;
import com.corenrn.gestaoprocessoetico.dto.ProcessoEticoDTO;
import com.corenrn.gestaoprocessoetico.dto.FasesProcessoDTO;
import com.corenrn.gestaoprocessoetico.service.ProcessoEticoService;
import com.corenrn.gestaoprocessoetico.service.FasesProcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/processo-etico")
@CrossOrigin(origins = "http://localhost:4200")
public class ProcessoEticoController {

    @Autowired
    private ProcessoEticoService processoEticoService;

    @Autowired
    private FasesProcessoService fasesProcessoService;

    @GetMapping("/{id}/fases")
    public ResponseEntity<List<FasesProcessoDTO>> listarFasesDoProcesso(@PathVariable Long id) {
        List<FasesProcessoDTO> fases = fasesProcessoService.findFasesByProcessoId(id)
                .stream()
                .map(FasesProcessoDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(fases);
    }

    @PostMapping
    public ResponseEntity<?> cadastroProcessoEtico(@RequestBody ProcessoEtico processoEtico) {
        try {
            ProcessoEtico novoProcessoEtico = processoEticoService.salvarProcessoEtico(processoEtico);
            return new ResponseEntity<>(new ProcessoEticoDTO(novoProcessoEtico), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar processo ético.");
        }
    }


    @GetMapping
    public ResponseEntity<List<ProcessoEticoDTO>> listarProcessoEtico() {
        List<ProcessoEticoDTO> lista = processoEticoService.findAllProcessoEtico()
                .stream()
                .map(ProcessoEticoDTO::new)
                .toList();
        return ResponseEntity.ok(lista);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProcessoEticoDTO> buscarPorId(@PathVariable Long id) {
        ProcessoEtico processo = processoEticoService.findProcessoEticoById(id);
        return processo != null ? ResponseEntity.ok(new ProcessoEticoDTO(processo)) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProcessoEticoDTO> atualizarProcessoEtico(@PathVariable Long id, @RequestBody ProcessoEtico processoAtualizado) {
        System.out.println("Recebendo atualização do Processo Ético: " + processoAtualizado);

        ProcessoEtico atualizado = processoEticoService.atualizarProcessoEtico(id, processoAtualizado);
        return atualizado != null ? ResponseEntity.ok(new ProcessoEticoDTO(atualizado)) : ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProcessoEtico(@PathVariable Long id) {
        processoEticoService.deletarProcessoEtico(id);
        return ResponseEntity.noContent().build();
    }
}
