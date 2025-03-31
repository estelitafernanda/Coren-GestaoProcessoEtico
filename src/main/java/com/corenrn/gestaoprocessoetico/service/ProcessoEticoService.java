package com.corenrn.gestaoprocessoetico.service;

import com.corenrn.gestaoprocessoetico.domain.FasesProcesso;
import com.corenrn.gestaoprocessoetico.domain.ProcessoEtico;
import com.corenrn.gestaoprocessoetico.exception.ProcessoEticoExistenteException;
import com.corenrn.gestaoprocessoetico.repository.FasesProcessoRepository;
import com.corenrn.gestaoprocessoetico.repository.ProcessoEticoRepository;
import jakarta.persistence.EntityNotFoundException;
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
        if (processo.getProcesso() != null && processo.getProcesso().getProcessId() != null) {
            System.out.println("Verificando processo ético para o processId: " + processo.getProcesso().getProcessId());
            Optional<ProcessoEtico> existente = processoEticoRepository.findByProcessId(processo.getProcesso().getProcessId());

            if (existente.isPresent()) {
                throw new ProcessoEticoExistenteException("Já existe um processo ético vinculado a este processo!");
            }
        }

        return processoEticoRepository.save(processo);
    }



    public ProcessoEtico findProcessoEticoById(Long processoEticoId) {
        return processoEticoRepository.findById(processoEticoId).orElse(null);
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
    public void deletarProcessoEtico(Long ethicalProcessId) {
        ProcessoEtico processoEtico = processoEticoRepository.findById(ethicalProcessId)
                .orElseThrow(() -> new EntityNotFoundException("Processo Ético não encontrado"));

        if (processoEtico.isTemFases()) {
            for (FasesProcesso fase : processoEtico.getFasesProcesso()) {
                fasesProcessoRepository.delete(fase);
            }
        }

        processoEticoRepository.delete(processoEtico);
    }


    public boolean existsByProcessId(Long processId) {
        return processoEticoRepository.findByProcessId(processId).isPresent();
    }

    public long contarProcessosEticos() {
        return processoEticoRepository.count();
    }
    public Optional<ProcessoEtico> findByProcessId(Long processId) {
        return processoEticoRepository.findByProcessId(processId);
    }

    public List<ProcessoEtico> getProcessosPertoDeExpirar(int dias) {
        LocalDate dataLimite = LocalDate.now().plusDays(dias);
        return processoEticoRepository.findByInspiraEmBefore((dataLimite.toString()));
    }


}
