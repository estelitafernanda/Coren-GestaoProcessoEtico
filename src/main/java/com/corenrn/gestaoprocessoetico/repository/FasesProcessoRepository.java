package com.corenrn.gestaoprocessoetico.repository;

import com.corenrn.gestaoprocessoetico.domain.FasesProcesso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FasesProcessoRepository extends JpaRepository<FasesProcesso, Long> {
    List<FasesProcesso> findByProcessoEticoEthicalProcessId(Long ethicalProcessId);
    @Transactional
    @Modifying
    @Query("DELETE FROM FasesProcesso f WHERE f.processoEtico.ethicalProcessId = :processoId")
    void deleteByProcessoId(@Param("processoId") Long processoId);
}

