package com.corenrn.gestaoprocessoetico.service;

import com.corenrn.gestaoprocessoetico.domain.FasesProcesso;
import com.corenrn.gestaoprocessoetico.repository.FasesProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FasesProcessoService {

    @Autowired
    private FasesProcessoRepository fasesProcessoRepository;

    public FasesProcesso saveFases(FasesProcesso fasesProcesso) {
        return fasesProcessoRepository.save(fasesProcesso);
    }

    public List<FasesProcesso> findAllFases() {
        return fasesProcessoRepository.findAll();
    }

    public Optional<FasesProcesso> findFasesProcessoById(Long id) {
        return fasesProcessoRepository.findById(id);
    }

    public FasesProcesso updateFasesProcesso(Long id, FasesProcesso fasesProcesso) {
        if (fasesProcessoRepository.existsById(id)) {
            fasesProcesso.setFasesId(id);
            return fasesProcessoRepository.save(fasesProcesso);
        }
        return null;
    }

    public void deleteFasesProcesso(Long id) {
        if (fasesProcessoRepository.existsById(id)) {
            fasesProcessoRepository.deleteById(id);
        }
    }
}
