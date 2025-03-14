package com.corenrn.gestaoprocessoetico.controller;

import com.corenrn.gestaoprocessoetico.domain.ProcessoEtico;
import com.corenrn.gestaoprocessoetico.service.FasesProcessoService;
import com.corenrn.gestaoprocessoetico.service.ProcessoEticoService;
import com.corenrn.gestaoprocessoetico.service.ProcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "http://localhost:4200")
public class DashboardController {

    @Autowired
    private ProcessoService processoService;

    @Autowired
    private ProcessoEticoService processoEticoService;

    @GetMapping("/total-processos")
    public ResponseEntity<Long> getTotalProcessos() {
        long total = processoService.getTotalProcessos();
        return ResponseEntity.ok(total);
    }

    @GetMapping("/total-processos-eticos")
    public ResponseEntity<Long> getTotalProcessosEticos() {
        long total = processoEticoService.contarProcessosEticos();
        return ResponseEntity.ok(total);
    }
    @GetMapping("/processos-expirando")
    public ResponseEntity<List<ProcessoEtico>> getProcessosPertoDeExpirar(@RequestParam(defaultValue = "7") int dias) {
        List<ProcessoEtico> processos = processoEticoService.getProcessosPertoDeExpirar(dias);
        return ResponseEntity.ok(processos);
    }
}
