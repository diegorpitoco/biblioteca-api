package br.com.bootcamp360.biblioteca.controllers.payload;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record EmprestimoRequest(
        @NotNull Long livroId,
        @NotNull Long usuarioId,
        LocalDate dataDevolucaoPrevista // opcional
) { }