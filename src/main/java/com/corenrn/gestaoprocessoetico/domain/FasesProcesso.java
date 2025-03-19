package com.corenrn.gestaoprocessoetico.domain;

import com.corenrn.gestaoprocessoetico.domain.ProcessoEtico;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Data
@Table(name="fasesprocesso")
public class FasesProcesso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fasesId;
    private String nameFase;
    private String prazoFase;

    @ManyToOne
    @JsonIgnore
    @JsonProperty("processoEtico")
    @JoinColumn(name = "ethical_process_id", nullable = false)
    private ProcessoEtico processoEtico;


}
