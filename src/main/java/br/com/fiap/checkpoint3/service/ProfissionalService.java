package br.com.fiap.checkpoint3.service;

import br.com.fiap.checkpoint3.model.Profissional;
import br.com.fiap.checkpoint3.repository.ProfissionalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfissionalService {

    private final ProfissionalRepository repository;

    public ProfissionalService(ProfissionalRepository repository) {
        this.repository = repository;
    }

    public Profissional salvar(Profissional profissional) {
        return repository.save(profissional);
    }

    public List<Profissional> listarTodos(String sort) {
        return "desc".equalsIgnoreCase(sort) ?
                repository.findAll(org.springframework.data.domain.Sort.by("nome").descending()) :
                repository.findAll(org.springframework.data.domain.Sort.by("nome").ascending());
    }

    public Optional<Profissional> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
