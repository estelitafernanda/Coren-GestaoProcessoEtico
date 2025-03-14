package com.corenrn.gestaoprocessoetico.service;

import com.corenrn.gestaoprocessoetico.domain.ProcessoEtico;
import com.corenrn.gestaoprocessoetico.repository.ProcessoEticoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProcessoEticoService {

    @Autowired
    private ProcessoEticoRepository processoEticoRepository;

    public ProcessoEtico salvarProcessoEtico(ProcessoEtico processo) {
        return processoEticoRepository.save(processo);
    }

    public ProcessoEtico findProcessoEticoById(Long id) {
        return processoEticoRepository.findById(id).orElse(null);
    }

    public List<ProcessoEtico> findAllProcessoEtico() {
        return processoEticoRepository.findAll();
    }

    public ProcessoEtico atualizarProcessoEtico(Long id, ProcessoEtico processoAtualizado) {
        Optional<ProcessoEtico> existente = processoEticoRepository.findById(id);
        if (existente.isPresent()) {
            ProcessoEtico processo = existente.get();
            processo.setNumberEthicalProcess(processoAtualizado.getNumberEthicalProcess());
            processo.setResponsible(processoAtualizado.getResponsible());
            processo.setDate(processoAtualizado.getDate());
            processo.setInspiraEm(processoAtualizado.getInspiraEm());
            return processoEticoRepository.save(processo);
        }
        return null;
    }

    public void deletarProcessoEtico(Long id) {
        if (processoEticoRepository.existsById(id)) {
            processoEticoRepository.deleteById(id);
        }
    }
    public long contarProcessosEticos() {
        return processoEticoRepository.count();
    }

    public List<ProcessoEtico> getProcessosPertoDeExpirar(int dias) {
        LocalDate dataLimite = LocalDate.now().plusDays(dias);
        return processoEticoRepository.findByInspiraEmBefore((dataLimite.toString()));
    }


}
