package br.com.fiap.checkpoint3.service;

import br.com.fiap.checkpoint3.model.Consulta;
import br.com.fiap.checkpoint3.model.Consulta.StatusConsulta;
import br.com.fiap.checkpoint3.repository.ConsultaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    private final ConsultaRepository repository;

    public ConsultaService(ConsultaRepository repository) {
        this.repository = repository;
    }

    public Consulta salvar(Consulta consulta) {
        return repository.save(consulta);
    }

    public List<Consulta> listarTodas() {
        return repository.findAll();
    }

    public Optional<Consulta> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public List<Consulta> buscarPorPaciente(Long id, StatusConsulta status, LocalDate dataDe, LocalDate dataAte) {
        return repository.findByPacienteWithFilters(id, status, dataDe, dataAte);
    }

    public List<Consulta> buscarPorProfissional(Long id, StatusConsulta status, LocalDate dataDe, LocalDate dataAte) {
        return repository.findByProfissionalWithFilters(id, status, dataDe, dataAte);
    }

    public List<Consulta> buscarComFiltros(StatusConsulta status, LocalDate dataDe, LocalDate dataAte) {
        return repository.findAllWithFilters(status, dataDe, dataAte);
    }

    public List<Object[]> buscarEstatisticasPorProfissional(Long id) {
        return repository.getStatsByProfissional(id);
    }
}
