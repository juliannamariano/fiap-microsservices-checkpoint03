package br.com.fiap.checkpoint3.model;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Profissional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String especialidade;
}

