package br.com.bootcamp360.biblioteca.converters;

import br.com.bootcamp360.biblioteca.controllers.payload.*;
import br.com.bootcamp360.biblioteca.entitys.Livro;

public class LivroMapper {
    public static Livro toEntity(LivroRequest r) {
        return Livro.builder()
                .titulo(r.titulo())
                .autor(r.autor())
                .isbn(r.isbn())
                .dataPublicacao(r.dataPublicacao())
                .disponivel(r.disponivel() == null ? true : r.disponivel())
                .build();
    }
    public static void updateEntity(Livro e, LivroRequest r) {
        e.setTitulo(r.titulo());
        e.setAutor(r.autor());
        e.setIsbn(r.isbn());
        e.setDataPublicacao(r.dataPublicacao());
        if (r.disponivel() != null) e.setDisponivel(r.disponivel());
    }
    public static LivroResponse toResponse(Livro l) {
        return new LivroResponse(l.getId(), l.getTitulo(), l.getAutor(),
                l.getIsbn(), l.getDataPublicacao(), l.getDisponivel());
    }
}