package br.com.bootcamp360.biblioteca.controllers.payload;

import java.time.LocalDate;

public record EmprestimoResponse(
        Long id,
        Long livroId,
        String livroTitulo,
        Long usuarioId,
        String usuarioNome,
        LocalDate dataEmprestimo,
        LocalDate dataDevolucaoPrevista,
        LocalDate dataDevolucaoReal,
        String status
) { }