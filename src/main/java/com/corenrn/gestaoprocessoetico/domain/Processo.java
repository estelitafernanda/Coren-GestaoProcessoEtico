package com.corenrn.gestaoprocessoetico.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@JsonIgnoreProperties({"processoEtico"})
@Data
@Entity
@Table(name = "processo")
public class Processo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long processId;

    private String numberProcess;

    @JsonProperty("isEthicalProcess")
    private boolean isEthicalProcess;

    private boolean belongsCofen;
    private String numberDenuncia;
    private String dateDenuncia;


    @OneToOne(mappedBy = "processo", cascade = CascadeType.ALL)
    private ProcessoEtico processoEtico;
}
