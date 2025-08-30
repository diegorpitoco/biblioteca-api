package br.com.bootcamp360.biblioteca.controllers;

import br.com.bootcamp360.biblioteca.controllers.payload.EmprestimoRequest;
import br.com.bootcamp360.biblioteca.controllers.payload.EmprestimoResponse;
import br.com.bootcamp360.biblioteca.services.EmprestimoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/emprestimos")
@RequiredArgsConstructor
public class EmprestimoController {

    private final EmprestimoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmprestimoResponse criar(@RequestBody @Valid EmprestimoRequest req) {
        return service.criar(req);
    }

    @PostMapping("/{id}/devolver")
    public EmprestimoResponse devolver(@PathVariable Long id) {
        return service.devolver(id);
    }

    @GetMapping
    public Page<EmprestimoResponse> listar(@ParameterObject Pageable pageable) {
        return service.listar(pageable);
    }

    @GetMapping("/{id}")
    public EmprestimoResponse buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}
