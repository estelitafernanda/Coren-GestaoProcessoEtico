package com.corenrn.gestaoprocessoetico.controller;

import com.corenrn.gestaoprocessoetico.domain.FasesProcesso;
import com.corenrn.gestaoprocessoetico.domain.ProcessoEtico;
import com.corenrn.gestaoprocessoetico.dto.FasesProcessoDTO;
import com.corenrn.gestaoprocessoetico.repository.FasesProcessoRepository;
import com.corenrn.gestaoprocessoetico.repository.ProcessoEticoRepository;
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

    @Autowired
    private FasesProcessoRepository fasesProcessoRepository;

    @Autowired
    private ProcessoEticoRepository processoEticoRepository;

    @PutMapping("/{id}/prazo")
    public ResponseEntity<FasesProcesso> atualizarPrazoFase(
            @PathVariable Long id,
            @RequestParam String novoPrazo) {

        FasesProcesso faseAtualizada = fasesProcessoService.atualizarPrazoFase(id, novoPrazo);
        return ResponseEntity.ok(faseAtualizada);
    }

    @PostMapping
    public ResponseEntity<?> cadastrarFase(@RequestBody FasesProcessoDTO dto) {
        System.out.println("Recebido JSON: " + dto);

        if (dto.getEthicalProcessoId() == null) {
            throw new RuntimeException("O campo 'processoEtico' está ausente!");
        }

        ProcessoEtico processoEtico = processoEticoRepository.findById(dto.getEthicalProcessoId())
                .orElseThrow(() -> new RuntimeException("Processo Ético não encontrado!"));


        FasesProcesso novaFase = new FasesProcesso();
        novaFase.setNameFase(dto.getNameFase());
        novaFase.setPrazoFase(dto.getPrazoFase());
        novaFase.setProcessoEtico(processoEtico);

        fasesProcessoRepository.save(novaFase);

        processoEtico.atualizarInspiraEm();
        processoEticoRepository.save(processoEtico);

        return ResponseEntity.ok("Fase cadastrada com sucesso!");
    }


    @GetMapping
    public ResponseEntity<List<FasesProcesso>> listarFasesProcesso() {
        List<FasesProcesso> lista = fasesProcessoService.findAllFases();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<FasesProcesso> buscarFaseProcessoPorId(@PathVariable("id") Long id) {
        Optional<FasesProcesso> faseProcesso = fasesProcessoService.findFasesProcessoById(id);

        if (faseProcesso.isPresent()) {
            System.out.println("Fase encontrada: " + faseProcesso.get());
            return ResponseEntity.ok(faseProcesso.get());
        } else {
            System.out.println("Fase não encontrada para o ID: " + id);
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<FasesProcesso> atualizarFaseProcesso(@PathVariable("id") Long id,
                                                               @RequestBody FasesProcesso fasesProcesso) {
        FasesProcesso faseAtualizada = fasesProcessoService.updateFasesProcesso(id, fasesProcesso);

        if (faseAtualizada != null) {
            System.out.println("Fase atualizada com sucesso: " + faseAtualizada.getFasesId() +
                    " do processo: " + faseAtualizada.getProcessoEtico().getEthicalProcessId());
            return ResponseEntity.ok(faseAtualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarFaseProcesso(@PathVariable("id") Long id) {
        fasesProcessoService.deleteFasesProcesso(id);
    }
}
