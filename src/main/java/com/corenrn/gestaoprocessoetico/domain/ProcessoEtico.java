package com.corenrn.gestaoprocessoetico.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    private LocalDate date;

    @Column(nullable = true)
    private String inspiraEm;

    public boolean isTemFases() {
        return fasesProcesso != null && !fasesProcesso.isEmpty();
    }

    @OneToOne
    @JoinColumn(name = "process_id", nullable = false, unique = true)
    private Processo processo;

    @OneToMany(mappedBy = "processoEtico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FasesProcesso> fasesProcesso = new ArrayList<>();

    public void atualizarInspiraEm() {
        if (isTemFases()) {
            LocalDate maxPrazo = fasesProcesso.stream()
                    .map(f -> {
                        try {
                            return LocalDate.parse(f.getPrazoFase(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        } catch (Exception e) {
                            return null; // Ignorar valores inválidos
                        }
                    })
                    .filter(date -> date != null)
                    .max(LocalDate::compareTo)
                    .orElse(null);

            this.inspiraEm = (maxPrazo != null) ? maxPrazo.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : null;
        } else {
            this.inspiraEm = null;
        }
    }

    public void setInspiraEm(String inspiraEm) {
        if (inspiraEm != null && !inspiraEm.isEmpty()) {
            try {
                // Verifica se a string está no formato correto antes de atribuir
                LocalDate.parse(inspiraEm, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                this.inspiraEm = inspiraEm;
            } catch (Exception e) {
                throw new IllegalArgumentException("Formato inválido para inspiraEm! Use yyyy-MM-dd");
            }
        } else {
            this.inspiraEm = null;
        }
    }
}
