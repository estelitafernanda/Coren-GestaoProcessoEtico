package com.corenrn.gestaoprocessoetico.service;

import com.corenrn.gestaoprocessoetico.domain.Processo;
import com.corenrn.gestaoprocessoetico.repository.ProcessoRepository;
import jakarta.transaction.Transactional;
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

    @Transactional
    public Processo atualizarProcesso(Long id, Processo processoAtualizado) {
        Optional<Processo> existente = processoRepository.findById(id);

        if (existente.isPresent()) {
            Processo processo = existente.get();

            // Atualiza os campos corretamente
            processo.setNumberProcess(processoAtualizado.getNumberProcess());
            processo.setNumberDenuncia(processoAtualizado.getNumberDenuncia());
            processo.setDateDenuncia(processoAtualizado.getDateDenuncia());
            processo.setEthicalProcess(processoAtualizado.isEthicalProcess());
            processo.setBelongsCofen(processoAtualizado.isBelongsCofen());

            return processoRepository.save(processo);
        }

        throw new RuntimeException("Processo não encontrado!");
    }

    public void excluirProcesso(Long id) {
        if (processoRepository.existsById(id)) {
            processoRepository.deleteById(id);
        }
    }
    public long getTotalProcessos() {
        return processoRepository.count();
    }

}
