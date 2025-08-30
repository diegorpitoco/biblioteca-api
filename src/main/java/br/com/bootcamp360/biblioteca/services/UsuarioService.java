package br.com.bootcamp360.biblioteca.services;

import br.com.bootcamp360.biblioteca.controllers.payload.UsuarioRequest;
import br.com.bootcamp360.biblioteca.controllers.payload.UsuarioResponse;
import br.com.bootcamp360.biblioteca.converters.UsuarioMapper;
import br.com.bootcamp360.biblioteca.entitys.Usuario;
import br.com.bootcamp360.biblioteca.repositorys.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repo;

    @Transactional
    public UsuarioResponse criar(UsuarioRequest req) {
        Usuario u = UsuarioMapper.toEntity(req);
        return UsuarioMapper.toResponse(repo.save(u));
    }

    @Transactional(readOnly = true)
    public Page<UsuarioResponse> listar(Pageable pageable) {
        return repo.findAll(pageable).map(UsuarioMapper::toResponse);
    }

    @Transactional(readOnly = true)
    public UsuarioResponse buscarPorId(Long id) {
        Usuario u = repo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Usuário id " + id + " não encontrado"));
        return UsuarioMapper.toResponse(u);
    }

    @Transactional
    public UsuarioResponse atualizar(Long id, UsuarioRequest req) {
        Usuario u = repo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Usuário id " + id + " não encontrado"));
        UsuarioMapper.updateEntity(u, req);
        return UsuarioMapper.toResponse(repo.save(u));
    }

    @Transactional
    public void excluir(Long id) {
        if (!repo.existsById(id)) throw new EntityNotFoundException("Usuário id " + id + " não encontrado");
        repo.deleteById(id);
    }
}