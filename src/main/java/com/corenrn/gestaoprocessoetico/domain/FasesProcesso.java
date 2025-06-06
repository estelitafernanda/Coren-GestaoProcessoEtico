package com.corenrn.gestaoprocessoetico.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="fasesprocesso")
public class FasesProcesso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fasesId;
    private String nameFase;
    private String prazoFase;

    @ManyToOne
    @JsonProperty("processoEtico")
    @JsonBackReference
    @JoinColumn(name = "ethical_process_id", nullable = false)
    private ProcessoEtico processoEtico;


}
