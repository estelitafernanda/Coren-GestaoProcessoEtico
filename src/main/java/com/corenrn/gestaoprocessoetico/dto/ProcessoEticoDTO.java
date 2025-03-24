package com.corenrn.gestaoprocessoetico.dto;

import com.corenrn.gestaoprocessoetico.domain.ProcessoEtico;
import com.corenrn.gestaoprocessoetico.dto.FasesProcessoDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ProcessoEticoDTO {
    private Long ethicalProcessId;
    private Long processId;
    private int numberEthicalProcess;
    private String responsible;
    private LocalDate date;
    private String inspiraEm;
    private List<FasesProcessoDTO> fasesProcesso;

    public ProcessoEticoDTO(ProcessoEtico processoEtico) {
        this.ethicalProcessId = processoEtico.getEthicalProcessId();
        this.processId = (processoEtico.getProcesso() != null) ? processoEtico.getProcesso().getProcessId() : null;
        this.numberEthicalProcess = processoEtico.getNumberEthicalProcess();
        this.responsible = processoEtico.getResponsible();
        this.date = processoEtico.getDate();
        this.inspiraEm = processoEtico.getInspiraEm();
        this.fasesProcesso = processoEtico.getFasesProcesso().stream()
                .map(FasesProcessoDTO::new)
                .toList();

    }
}

