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

    public void setPrazoFase(LocalDate prazoFase) {
        this.prazoFase = prazoFase;
        atualizarInspiraEm();
    }

    private void atualizarInspiraEm() {
        if (processoEtico != null && processoEtico.getDate() != null && prazoFase != null) {
            LocalDate novaData = processoEtico.getDate().plusDays(prazoFase.getDayOfYear());
            processoEtico.setInspiraEm(novaData.toString());
        }
    }
}
