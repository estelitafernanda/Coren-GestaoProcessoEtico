package com.corenrn.gestaoprocessoetico.controller;

import com.corenrn.gestaoprocessoetico.domain.ProcessoEtico;
import com.corenrn.gestaoprocessoetico.service.ProcessoEticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/processo-etico")
@CrossOrigin(origins = "http://localhost:4200")
public class ProcessoEticoController {

    @Autowired
    private ProcessoEticoService processoEticoService;

    @PostMapping
    public ResponseEntity<ProcessoEtico> cadastroProcessoEtico(@RequestBody ProcessoEtico processoEtico) {
        ProcessoEtico novoProcessoEtico = processoEticoService.salvarProcessoEtico(processoEtico);
        return new ResponseEntity<>(novoProcessoEtico, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<ProcessoEtico>> listarProcessoEtico() {
        List<ProcessoEtico> lista = processoEticoService.findAllProcessoEtico();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
}
