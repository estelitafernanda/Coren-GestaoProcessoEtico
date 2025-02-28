package com.corenrn.gestaoprocessoetico.controller;

import com.corenrn.gestaoprocessoetico.domain.Processo;
import com.corenrn.gestaoprocessoetico.service.ProcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/processo")
@CrossOrigin(origins = "http://localhost:4200")
public class ProcessoController {

    @Autowired
    private ProcessoService processoService;

    @PostMapping
    public ResponseEntity<Processo> cadastroProcesso(@RequestBody Processo processo){
        Processo novoProcesso = processoService.salvarProcesso(processo);
        return new ResponseEntity<>(novoProcesso, HttpStatus.CREATED);
    }
    @GetMapping("/lista")
    public ResponseEntity<List<Processo>> listarProcessos(){
        List<Processo> lista = processoService.listaProcessos();
        return new ResponseEntity<>(lista, HttpStatus.OK);

    }
}
