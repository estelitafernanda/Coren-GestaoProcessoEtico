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
import java.util.stream.Collectors;

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
    public ResponseEntity<FasesProcessoDTO> atualizarPrazoFase(@PathVariable Long id, @RequestParam String novoPrazo) {
        FasesProcesso faseAtualizada = fasesProcessoService.atualizarPrazoFase(id, novoPrazo);
        return ResponseEntity.ok(new FasesProcessoDTO(faseAtualizada));
    }

    @PostMapping
    public ResponseEntity<?> cadastrarFase(@RequestBody FasesProcessoDTO dto) {
        System.out.println("Recebido JSON: " + dto);

        if (dto.getEthicalProcessoId() == null) {
            throw new RuntimeException("O campo 'ethicalProcessoId' está ausente!");
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

        return ResponseEntity.ok(new FasesProcessoDTO(novaFase));
    }

    @GetMapping
    public ResponseEntity<List<FasesProcessoDTO>> listarFasesProcesso() {
        List<FasesProcessoDTO> lista = fasesProcessoService.findAllFases()
                .stream()
                .map(FasesProcessoDTO::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FasesProcessoDTO> buscarFaseProcessoPorId(@PathVariable("id") Long id) {
        Optional<FasesProcesso> faseProcesso = fasesProcessoService.findFasesProcessoById(id);

        if (faseProcesso.isPresent()) {
            return ResponseEntity.ok(new FasesProcessoDTO(faseProcesso.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<FasesProcessoDTO> atualizarFaseProcesso(@PathVariable("id") Long id,
                                                                  @RequestBody FasesProcessoDTO dto) {
        Optional<FasesProcesso> faseOptional = fasesProcessoService.findFasesProcessoById(id);
        if (faseOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        FasesProcesso fase = faseOptional.get();
        fase.setNameFase(dto.getNameFase());
        fase.setPrazoFase(dto.getPrazoFase());

        FasesProcesso faseAtualizada = fasesProcessoService.updateFasesProcesso(id, fase);
        return ResponseEntity.ok(new FasesProcessoDTO(faseAtualizada));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarFaseProcesso(@PathVariable("id") Long id) {
        fasesProcessoService.deleteFasesProcesso(id);
    }
}
