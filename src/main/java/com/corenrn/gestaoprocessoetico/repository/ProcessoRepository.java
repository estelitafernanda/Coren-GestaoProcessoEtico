package com.corenrn.gestaoprocessoetico.repository;

import com.corenrn.gestaoprocessoetico.domain.Processo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessoRepository extends JpaRepository<Processo, Long> {
}
