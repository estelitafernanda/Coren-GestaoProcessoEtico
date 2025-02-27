package com.corenrn.gestaoprocessoetico.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "processoetico")
public class ProcessoEtico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ethical_process_id")
    private Long ethicalProcessId;

    private int numberEthicalProcess;
    private String responsible;
    private boolean validity;


    @OneToOne
    @JoinColumn(name = "process_id", nullable = false, unique = true)
    private Processo processo;

    @OneToMany(mappedBy = "processoEtico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FasesProcesso> fasesProcesso = new ArrayList<>();


}
