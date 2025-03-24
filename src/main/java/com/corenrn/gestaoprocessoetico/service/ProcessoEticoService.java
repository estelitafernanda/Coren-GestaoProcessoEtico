package com.corenrn.gestaoprocessoetico.service;

import com.corenrn.gestaoprocessoetico.domain.ProcessoEtico;
import com.corenrn.gestaoprocessoetico.repository.FasesProcessoRepository;
import com.corenrn.gestaoprocessoetico.repository.ProcessoEticoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProcessoEticoService {

    @Autowired
    private ProcessoEticoRepository processoEticoRepository;

    @Autowired
    private FasesProcessoRepository fasesProcessoRepository;

    public ProcessoEtico salvarProcessoEtico(ProcessoEtico processo) {
        if (processo.getProcesso() == null || processo.getProcesso().getProcessId() == null) {
            throw new IllegalArgumentException("O processo deve ser informado!");
        }

        // Verifica se já existe um processo ético vinculado ao mesmo processo
        Optional<ProcessoEtico> existente = processoEticoRepository.findByProcessoId(processo.getProcesso().getProcessId());
        if (existente.isPresent()) {
            throw new RuntimeException("Já existe um processo ético vinculado a este processo!");
        }

        return processoEticoRepository.save(processo);
    }

    public ProcessoEtico findProcessoEticoById(Long id) {
        return processoEticoRepository.findById(id).orElse(null);
    }

    public List<ProcessoEtico> findAllProcessoEtico() {
        return processoEticoRepository.findAll();
    }

    public ProcessoEtico atualizarProcessoEtico(Long id, ProcessoEtico processoAtualizado) {
        Optional<ProcessoEtico> optionalProcesso = processoEticoRepository.findById(id);

        if (optionalProcesso.isPresent()) {
            ProcessoEtico processoExistente = optionalProcesso.get();

            processoExistente.setNumberEthicalProcess(processoAtualizado.getNumberEthicalProcess());
            processoExistente.setResponsible(processoAtualizado.getResponsible());
            processoExistente.setDate(processoAtualizado.getDate());

            processoExistente.atualizarInspiraEm();

            return processoEticoRepository.save(processoExistente);
        } else {
            return null;
        }
    }

    @Transactional
    public void deletarProcessoEtico(Long id) {
        if (processoEticoRepository.existsById(id)) {
            fasesProcessoRepository.deleteByProcessoId(id);
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
