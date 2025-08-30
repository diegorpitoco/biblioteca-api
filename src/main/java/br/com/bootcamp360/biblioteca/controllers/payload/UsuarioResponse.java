package br.com.bootcamp360.biblioteca.controllers.payload;

public record UsuarioResponse(
        Long id, String nome, String email, String matricula, Boolean ativo) {
}