package br.com.fiap.checkpoint3.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate data;

    @Enumerated(EnumType.STRING)
    private StatusConsulta status;

    @ManyToOne
    private Paciente paciente;

    @ManyToOne
    private Profissional profissional;

    public enum StatusConsulta {
        AGENDADA, REALIZADA, CANCELADA
    }
}

