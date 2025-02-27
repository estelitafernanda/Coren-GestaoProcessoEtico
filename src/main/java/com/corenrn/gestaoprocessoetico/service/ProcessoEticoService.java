package com.corenrn.gestaoprocessoetico.service;

import com.corenrn.gestaoprocessoetico.domain.Processo;
import com.corenrn.gestaoprocessoetico.domain.ProcessoEtico;
import com.corenrn.gestaoprocessoetico.repository.ProcessoEticoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
