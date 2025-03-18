package com.corenrn.gestaoprocessoetico.domain;

import com.corenrn.gestaoprocessoetico.domain.ProcessoEtico;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JoinColumn(name = "ethical_process_id", nullable = false)
    private ProcessoEtico processoEtico;

    public void setPrazoFase(String prazoFase) {
        if (prazoFase != null && !prazoFase.isEmpty()) {
            try {
                LocalDate.parse(prazoFase, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                this.prazoFase = prazoFase;
            } catch (Exception e) {
                throw new IllegalArgumentException("Formato inválido para prazoFase! Use yyyy-MM-dd");
            }
        } else {
            this.prazoFase = null;
        }
    }

    @PrePersist
    @PreUpdate
    public void atualizarProcessoEtico() {
        if (processoEtico != null) {
            processoEtico.atualizarInspiraEm();
        }
    }
}
