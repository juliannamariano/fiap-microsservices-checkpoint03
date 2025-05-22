package br.com.fiap.checkpoint3.controller;

import br.com.fiap.checkpoint3.model.Profissional;
import br.com.fiap.checkpoint3.service.ProfissionalService;
import br.com.fiap.checkpoint3.service.ConsultaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profissionais")
public class ProfissionalController {

    private final ProfissionalService service;
    private final ConsultaService consultaService;

    public ProfissionalController(ProfissionalService service, ConsultaService consultaService) {
        this.service = service;
        this.consultaService = consultaService;
    }

    @PostMapping
    public ResponseEntity<Profissional> salvar(@RequestBody Profissional profissional) {
        return ResponseEntity.ok(service.salvar(profissional));
    }

    @GetMapping
    public ResponseEntity<List<Profissional>> listar(@RequestParam(defaultValue = "asc") String sort) {
        return ResponseEntity.ok(service.listarTodos(sort));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profissional> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profissional> atualizar(@PathVariable Long id, @RequestBody Profissional novo) {
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

    @GetMapping("/{id}/stats")
    public ResponseEntity<List<Object[]>> buscarStats(@PathVariable Long id) {
        return ResponseEntity.ok(consultaService.buscarEstatisticasPorProfissional(id));
    }
}
