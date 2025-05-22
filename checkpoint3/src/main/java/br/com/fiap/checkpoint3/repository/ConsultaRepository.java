package br.com.fiap.checkpoint3.repository;

import br.com.fiap.checkpoint3.model.Consulta;
import br.com.fiap.checkpoint3.model.Consulta.StatusConsulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    // Filtros por paciente
    @Query("SELECT c FROM Consulta c WHERE c.paciente.id = :idPaciente " +
            "AND (:status IS NULL OR c.status = :status) " +
            "AND (:dataDe IS NULL OR c.data >= :dataDe) " +
            "AND (:dataAte IS NULL OR c.data <= :dataAte)")
    List<Consulta> findByPacienteWithFilters(
            @Param("idPaciente") Long idPaciente,
            @Param("status") StatusConsulta status,
            @Param("dataDe") LocalDate dataDe,
            @Param("dataAte") LocalDate dataAte
    );

    // Filtros por profissional
    @Query("SELECT c FROM Consulta c WHERE c.profissional.id = :idProfissional " +
            "AND (:status IS NULL OR c.status = :status) " +
            "AND (:dataDe IS NULL OR c.data >= :dataDe) " +
            "AND (:dataAte IS NULL OR c.data <= :dataAte)")
    List<Consulta> findByProfissionalWithFilters(
            @Param("idProfissional") Long idProfissional,
            @Param("status") StatusConsulta status,
            @Param("dataDe") LocalDate dataDe,
            @Param("dataAte") LocalDate dataAte
    );

    // Filtro geral
    @Query("SELECT c FROM Consulta c WHERE " +
            "(:status IS NULL OR c.status = :status) " +
            "AND (:dataDe IS NULL OR c.data >= :dataDe) " +
            "AND (:dataAte IS NULL OR c.data <= :dataAte)")
    List<Consulta> findAllWithFilters(
            @Param("status") StatusConsulta status,
            @Param("dataDe") LocalDate dataDe,
            @Param("dataAte") LocalDate dataAte
    );

    // Estatísticas do profissional (total por status)
    @Query("SELECT c.status, COUNT(c) FROM Consulta c WHERE c.profissional.id = :id GROUP BY c.status")
    List<Object[]> getStatsByProfissional(@Param("id") Long idProfissional);
}

