package br.com.bootcamp360.biblioteca.controllers.payload;

import java.time.LocalDate;

public record LivroResponse(
        Long id, String titulo, String autor, String isbn,
        LocalDate dataPublicacao, Boolean disponivel
) {}