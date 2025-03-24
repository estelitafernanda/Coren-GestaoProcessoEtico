package com.corenrn.gestaoprocessoetico.dto;

import com.corenrn.gestaoprocessoetico.domain.FasesProcesso;
import com.corenrn.gestaoprocessoetico.domain.ProcessoEtico;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FasesProcessoDTO {
    private Long fasesId;
    private String nameFase;
    private String prazoFase;
    private Long ethicalProcessoId;

    public FasesProcessoDTO() {
    }

    public FasesProcessoDTO(Long fasesId, String nameFase, String prazoFase, Long ethicalProcessoId) {
        this.fasesId = fasesId;
        this.nameFase = nameFase;
        this.prazoFase = prazoFase;
        this.ethicalProcessoId = ethicalProcessoId;
    }

    public FasesProcessoDTO(FasesProcesso fasesProcesso) {
        this.fasesId = fasesProcesso.getFasesId();
        this.nameFase = fasesProcesso.getNameFase();
        this.prazoFase = fasesProcesso.getPrazoFase();
        this.ethicalProcessoId = fasesProcesso.getProcessoEtico().getEthicalProcessId();
    }

}

