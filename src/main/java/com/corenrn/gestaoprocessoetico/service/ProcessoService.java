package com.corenrn.gestaoprocessoetico.service;

import com.corenrn.gestaoprocessoetico.domain.Processo;
import com.corenrn.gestaoprocessoetico.repository.ProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProcessoService {

    @Autowired
    private ProcessoRepository processoRepository;

    public Processo salvarProcesso(Processo processo) {
        return processoRepository.save(processo);
    }

    public List<Processo> listaProcessos() {
        return processoRepository.findAll();
    }

    public Processo buscarProcesso(Long id) {
        return processoRepository.findById(id).orElse(null);
    }

    public Processo atualizarProcesso(Long id, Processo processoAtualizado) {
        Optional<Processo> existente = processoRepository.findById(id);
        if (existente.isPresent()) {
            Processo processo = existente.get();
            return processoRepository.save(processo);
        }
        return null;
    }

    public void excluirProcesso(Long id) {
        if (processoRepository.existsById(id)) {
            processoRepository.deleteById(id);
        }
    }
}
