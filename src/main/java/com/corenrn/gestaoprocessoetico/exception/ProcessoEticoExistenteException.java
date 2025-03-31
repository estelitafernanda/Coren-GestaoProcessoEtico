package com.corenrn.gestaoprocessoetico.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProcessoEticoExistenteException extends RuntimeException {

    public ProcessoEticoExistenteException(String message) {
        super(message);
    }
}
