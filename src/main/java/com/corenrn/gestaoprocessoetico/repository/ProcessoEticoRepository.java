package com.corenrn.gestaoprocessoetico.repository;

import com.corenrn.gestaoprocessoetico.domain.ProcessoEtico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProcessoEticoRepository extends JpaRepository<ProcessoEtico, Long> {
    List<ProcessoEtico> findByInspiraEmBefore(String inspiraEmBefore);

    @Query(value = "SELECT * FROM processoetico WHERE process_id = :process_id", nativeQuery = true)
    Optional<ProcessoEtico> findByProcessId(@Param("process_id") Long processId);



}
