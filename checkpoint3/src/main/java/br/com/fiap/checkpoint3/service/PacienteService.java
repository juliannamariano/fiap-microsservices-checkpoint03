package br.com.fiap.checkpoint3.service;

import br.com.fiap.checkpoint3.model.Paciente;
import br.com.fiap.checkpoint3.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    private final PacienteRepository repository;

    public PacienteService(PacienteRepository repository) {
        this.repository = repository;
    }

    public Paciente salvar(Paciente paciente) {
        return repository.save(paciente);
    }

    public List<Paciente> listarTodos(String sort) {
        return "desc".equalsIgnoreCase(sort) ?
                repository.findAll(org.springframework.data.domain.Sort.by("nome").descending()) :
                repository.findAll(org.springframework.data.domain.Sort.by("nome").ascending());
    }

    public Optional<Paciente> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
