package com.corenrn.gestaoprocessoetico.repository;

import com.corenrn.gestaoprocessoetico.domain.FasesProcesso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FasesProcessoRepository extends JpaRepository<FasesProcesso, Long> {
    List<FasesProcesso> findByProcessoEticoEthicalProcessId(Long ethicalProcessId);
}
