package com.corenrn.gestaoprocessoetico.repository;

import com.corenrn.gestaoprocessoetico.domain.ProcessoEtico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProcessoEticoRepository extends JpaRepository<ProcessoEtico, Long> {
    List<ProcessoEtico> findByInspiraEmBefore(String inspiraEmBefore);
}
