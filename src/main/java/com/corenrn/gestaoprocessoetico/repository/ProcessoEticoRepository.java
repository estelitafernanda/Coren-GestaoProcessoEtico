package com.corenrn.gestaoprocessoetico.repository;

import com.corenrn.gestaoprocessoetico.domain.ProcessoEtico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessoEticoRepository extends JpaRepository<ProcessoEtico, Long> {
}
