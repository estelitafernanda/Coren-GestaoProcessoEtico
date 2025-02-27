package com.corenrn.gestaoprocessoetico.domain;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name="fasesprocesso")
public class FasesProcesso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fasesId;

    private String nameFase;
    private LocalDate prazoFase;

    @ManyToOne
    @JoinColumn(name = "ethical_process_id", nullable = false)
    private ProcessoEtico processoEtico;
}
