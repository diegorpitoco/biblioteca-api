package br.com.bootcamp360.biblioteca.entitys;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "TB_USUARIO")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Size(max = 100)
    private String nome;

    @Email @Size(max = 120)
    @Column(unique = true)
    private String email;

    @Size(max = 30)
    @Column(unique = true)
    private String matricula;

    @Column(nullable = false)
    private Boolean ativo = true;
}