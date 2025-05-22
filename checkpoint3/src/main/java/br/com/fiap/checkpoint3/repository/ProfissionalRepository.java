package br.com.fiap.checkpoint3.repository;

import br.com.fiap.checkpoint3.model.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
}
