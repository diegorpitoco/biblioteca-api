package br.com.bootcamp360.biblioteca.converters;

import br.com.bootcamp360.biblioteca.controllers.payload.UsuarioRequest;
import br.com.bootcamp360.biblioteca.controllers.payload.UsuarioResponse;
import br.com.bootcamp360.biblioteca.entitys.Usuario;

public class UsuarioMapper {
    public static Usuario toEntity(UsuarioRequest r) {
        return Usuario.builder()
                .nome(r.nome())
                .email(r.email())
                .matricula(r.matricula())
                .ativo(r.ativo() == null ? true : r.ativo())
                .build();
    }
    public static void updateEntity(Usuario u, UsuarioRequest r) {
        u.setNome(r.nome());
        u.setEmail(r.email());
        u.setMatricula(r.matricula());
        if (r.ativo() != null) u.setAtivo(r.ativo());
    }
    public static UsuarioResponse toResponse(Usuario u) {
        return new UsuarioResponse(u.getId(), u.getNome(), u.getEmail(), u.getMatricula(), u.getAtivo());
    }
}