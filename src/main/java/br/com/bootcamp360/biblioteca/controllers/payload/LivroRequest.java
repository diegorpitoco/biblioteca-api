package br.com.bootcamp360.biblioteca.controllers.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record LivroRequest(
        @NotBlank @Size(max=120) String titulo,
        @NotBlank @Size(max=80)  String autor,
        @Size(max=20) String isbn,
        LocalDate dataPublicacao,
        Boolean disponivel
) {}
