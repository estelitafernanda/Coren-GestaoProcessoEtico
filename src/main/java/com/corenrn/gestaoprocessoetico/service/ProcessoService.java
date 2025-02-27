package com.corenrn.gestaoprocessoetico.service;

import com.corenrn.gestaoprocessoetico.domain.Processo;
import com.corenrn.gestaoprocessoetico.repository.ProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessoService {

    @Autowired
    private ProcessoRepository processoRepository;

    public Processo salvarProcesso(Processo processo) {
        return processoRepository.save(processo);
    }
    public List<Processo> listaProcessos(){
        return processoRepository.findAll();
    }
    public Processo buscarProcesso(Long id) {
        return processoRepository.findById(id).orElse(null);
    }
    public void excluirProcesso(Long id) {}


}
