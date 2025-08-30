package br.com.bootcamp360.biblioteca.converters;

import br.com.bootcamp360.biblioteca.controllers.payload.EmprestimoResponse;
import br.com.bootcamp360.biblioteca.entitys.Emprestimo;

public class EmprestimoMapper {
    public static EmprestimoResponse toResponse(Emprestimo e) {
        return new EmprestimoResponse(
                e.getId(),
                e.getLivro().getId(),
                e.getLivro().getTitulo(),
                e.getUsuario().getId(),
                e.getUsuario().getNome(),
                e.getDataEmprestimo(),
                e.getDataDevolucaoPrevista(),
                e.getDataDevolucaoReal(),
                e.getStatus().name()
        );
    }
}
