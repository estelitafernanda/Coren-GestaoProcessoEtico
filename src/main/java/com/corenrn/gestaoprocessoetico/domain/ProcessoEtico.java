package com.corenrn.gestaoprocessoetico.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
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
            int totalDias = fasesProcesso.stream()
                    .mapToInt(f -> {
                        try {
                            return Integer.parseInt(f.getPrazoFase());
                        } catch (Exception e) {
                            return 0;
                        }
                    })
                    .sum();

            LocalDate novaDataInspira = this.date.plusDays(totalDias);
            this.inspiraEm = novaDataInspira.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } else {
            this.inspiraEm = null;
        }
    }
}
