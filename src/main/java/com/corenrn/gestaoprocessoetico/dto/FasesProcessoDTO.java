package com.corenrn.gestaoprocessoetico.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FasesProcessoDTO {
    private String nameFase;
    private String prazoFase;
    private Long processoEtico;
}
