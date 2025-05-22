package br.com.fiap.checkpoint3.controller;

import br.com.fiap.checkpoint3.model.Consulta;
import br.com.fiap.checkpoint3.model.Consulta.StatusConsulta;
import br.com.fiap.checkpoint3.service.ConsultaService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private final ConsultaService service;

    public ConsultaController(ConsultaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Consulta> salvar(@RequestBody Consulta consulta) {
        return ResponseEntity.ok(service.salvar(consulta));
    }

    @GetMapping
    public ResponseEntity<List<Consulta>> listarTodas(
            @RequestParam(required = false) StatusConsulta status,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate data_de,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate data_ate
    ) {
        return ResponseEntity.ok(service.buscarComFiltros(status, data_de, data_ate));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consulta> atualizar(@PathVariable Long id, @RequestBody Consulta nova) {
        return service.buscarPorId(id)
                .map(c -> {
                    nova.setId(id);
                    return ResponseEntity.ok(service.salvar(nova));
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pacientes/{id}/consultas")
    public ResponseEntity<List<Consulta>> listarPorPaciente(
            @PathVariable Long id,
            @RequestParam(required = false) StatusConsulta status,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate data_de,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate data_ate
    ) {
        return ResponseEntity.ok(service.buscarPorPaciente(id, status, data_de, data_ate));
    }

    @GetMapping("/profissionais/{id}/consultas")
    public ResponseEntity<List<Consulta>> listarPorProfissional(
            @PathVariable Long id,
            @RequestParam(required = false) StatusConsulta status,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate data_de,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate data_ate
    ) {
        return ResponseEntity.ok(service.buscarPorProfissional(id, status, data_de, data_ate));
    }
}
