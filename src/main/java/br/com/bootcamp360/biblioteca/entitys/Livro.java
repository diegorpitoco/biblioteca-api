package br.com.bootcamp360.biblioteca.entitys;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "TB_LIVRO")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Livro {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Size(max = 120)
    private String titulo;

    @NotBlank @Size(max = 80)
    private String autor;

    @Size(max = 20)
    private String isbn;

    private LocalDate dataPublicacao;

    @Column(nullable = false)
    private Boolean disponivel = true;
}
