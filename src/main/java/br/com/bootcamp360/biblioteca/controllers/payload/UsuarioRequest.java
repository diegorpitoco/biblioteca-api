package br.com.bootcamp360.biblioteca.controllers.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioRequest(
        @NotBlank @Size(max=100) String nome,
        @Email @Size(max=120) String email,
        @Size(max=30) String matricula,
        Boolean ativo
) { }