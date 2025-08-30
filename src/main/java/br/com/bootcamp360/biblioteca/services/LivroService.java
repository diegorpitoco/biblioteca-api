package br.com.bootcamp360.biblioteca.services;

import br.com.bootcamp360.biblioteca.controllers.payload.*;
import br.com.bootcamp360.biblioteca.converters.LivroMapper;
import br.com.bootcamp360.biblioteca.entitys.Livro;
import br.com.bootcamp360.biblioteca.repositorys.LivroRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LivroService {
    private final LivroRepository repo;

    @Transactional
    public LivroResponse criar(LivroRequest req) {
        Livro entity = LivroMapper.toEntity(req);
        return LivroMapper.toResponse(repo.save(entity));
    }
    @Transactional(readOnly = true)
    public Page<LivroResponse> listar(Pageable pageable) {
        return repo.findAll(pageable).map(LivroMapper::toResponse);
    }
    @Transactional(readOnly = true)
    public LivroResponse buscarPorId(Long id) {
        Livro l = repo.findById(id).orElseThrow(
            () -> new EntityNotFoundException("Livro id " + id + " não encontrado"));
        return LivroMapper.toResponse(l);
    }
    @Transactional
    public LivroResponse atualizar(Long id, LivroRequest req) {
        Livro l = repo.findById(id).orElseThrow(
            () -> new EntityNotFoundException("Livro id " + id + " não encontrado"));
        LivroMapper.updateEntity(l, req);
        return LivroMapper.toResponse(repo.save(l));
    }
    @Transactional
    public void excluir(Long id) {
        if (!repo.existsById(id)) throw new EntityNotFoundException("Livro id " + id + " não encontrado");
        repo.deleteById(id);
    }
}