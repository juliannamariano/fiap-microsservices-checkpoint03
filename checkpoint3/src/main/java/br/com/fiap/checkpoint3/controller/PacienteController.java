package br.com.fiap.checkpoint3.controller;

import br.com.fiap.checkpoint3.model.Paciente;
import br.com.fiap.checkpoint3.service.PacienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService service;

    public PacienteController(PacienteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Paciente> salvar(@RequestBody Paciente paciente) {
        return ResponseEntity.ok(service.salvar(paciente));
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listar(@RequestParam(defaultValue = "asc") String sort) {
        return ResponseEntity.ok(service.listarTodos(sort));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> atualizar(@PathVariable Long id, @RequestBody Paciente novo) {
        return service.buscarPorId(id)
                .map(p -> {
                    novo.setId(id);
                    return ResponseEntity.ok(service.salvar(novo));
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
