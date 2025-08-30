package br.com.bootcamp360.biblioteca.services;

import br.com.bootcamp360.biblioteca.controllers.payload.EmprestimoRequest;
import br.com.bootcamp360.biblioteca.controllers.payload.EmprestimoResponse;
import br.com.bootcamp360.biblioteca.converters.EmprestimoMapper;
import br.com.bootcamp360.biblioteca.entitys.*;
import br.com.bootcamp360.biblioteca.repositorys.EmprestimoRepository;
import br.com.bootcamp360.biblioteca.repositorys.LivroRepository;
import br.com.bootcamp360.biblioteca.repositorys.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class EmprestimoService {

    private final EmprestimoRepository repo;
    private final LivroRepository livroRepo;
    private final UsuarioRepository usuarioRepo;

    @Transactional
    public EmprestimoResponse criar(EmprestimoRequest req) {
        Livro livro = livroRepo.findById(req.livroId())
                .orElseThrow(() -> new EntityNotFoundException("Livro id " + req.livroId() + " não encontrado"));
        if (Boolean.FALSE.equals(livro.getDisponivel())) {
            throw new IllegalStateException("Livro indisponível para empréstimo");
        }

        Usuario usuario = usuarioRepo.findById(req.usuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário id " + req.usuarioId() + " não encontrado"));

        Emprestimo e = Emprestimo.builder()
                .livro(livro)
                .usuario(usuario)
                .dataEmprestimo(LocalDate.now())
                .dataDevolucaoPrevista(req.dataDevolucaoPrevista())
                .status(StatusEmprestimo.ABERTO)
                .build();

        // marca livro indisponível
        livro.setDisponivel(false);
        livroRepo.save(livro);

        return EmprestimoMapper.toResponse(repo.save(e));
    }

    @Transactional
    public EmprestimoResponse devolver(Long id) {
        Emprestimo e = repo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Empréstimo id " + id + " não encontrado"));

        if (e.getStatus() == StatusEmprestimo.DEVOLVIDO) {
            return EmprestimoMapper.toResponse(e); // já devolvido
        }

        e.setStatus(StatusEmprestimo.DEVOLVIDO);
        e.setDataDevolucaoReal(LocalDate.now());
        repo.save(e);

        // libera o livro
        Livro livro = e.getLivro();
        livro.setDisponivel(true);
        livroRepo.save(livro);

        return EmprestimoMapper.toResponse(e);
    }

    @Transactional(readOnly = true)
    public Page<EmprestimoResponse> listar(Pageable pageable) {
        return repo.findAll(pageable).map(EmprestimoMapper::toResponse);
    }

    @Transactional(readOnly = true)
    public EmprestimoResponse buscarPorId(Long id) {
        Emprestimo e = repo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Empréstimo id " + id + " não encontrado"));
        return EmprestimoMapper.toResponse(e);
    }

    @Transactional
    public void excluir(Long id) {
        if (!repo.existsById(id)) throw new EntityNotFoundException("Empréstimo id " + id + " não encontrado");
        repo.deleteById(id);
    }
}