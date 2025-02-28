package com.corenrn.gestaoprocessoetico.service;

import com.corenrn.gestaoprocessoetico.domain.FasesProcesso;
import com.corenrn.gestaoprocessoetico.repository.FasesProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FasesProcessoService {

    @Autowired
    private FasesProcessoRepository fasesProcessoRepository;

    public FasesProcesso saveFases(FasesProcesso fasesProcesso) {
        return fasesProcessoRepository.save(fasesProcesso);
    }

}
