package com.corenrn.gestaoprocessoetico.repository;

import com.corenrn.gestaoprocessoetico.domain.ProcessoEtico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ProcessoEticoRepository extends JpaRepository<ProcessoEtico, Long> {
    List<ProcessoEtico> findByInspiraEmBefore(String inspiraEmBefore);

    @Query("SELECT pe FROM ProcessoEtico pe WHERE pe.processo.processId = :processId")
    Optional<ProcessoEtico> findByProcessoId(@Param("processId") Long processId);
}
