package br.com.bootcamp360.biblioteca.controllers;

import br.com.bootcamp360.biblioteca.controllers.payload.LivroRequest;
import br.com.bootcamp360.biblioteca.controllers.payload.LivroResponse;
import br.com.bootcamp360.biblioteca.services.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.mapping.PropertyReferenceException;

import java.util.*;

@RestController
@RequestMapping("/api/v1/livros")
@RequiredArgsConstructor
public class LivroController {

    private final LivroService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LivroResponse criar(@RequestBody @Valid LivroRequest req) {
        return service.criar(req);
    }

    // GET robusto contra sort=["string"] do Swagger
    @GetMapping
    public Page<LivroResponse> listar(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false) List<String> sort // aceita "titulo,asc" ou ["titulo,asc"]
    ) {
        // 1) Normaliza entradas vindas do Swagger (remove colchetes/aspas)
        List<String> cleaned = new ArrayList<>();
        if (sort != null) {
            for (String s : sort) {
                if (s == null) continue;
                s = s.trim();
                if (s.startsWith("[") && s.endsWith("]")) {
                    s = s.substring(1, s.length() - 1);
                }
                s = s.replace("\"", "").trim();
                if (!s.isBlank()) cleaned.add(s);
            }
        }

        // 2) Whitelist de propriedades válidas
        Set<String> allowedProps = Set.of("titulo", "autor", "isbn", "dataPublicacao", "disponivel");

        Sort sortObj = Sort.unsorted();
        if (!cleaned.isEmpty()) {
            List<Sort.Order> orders = new ArrayList<>();
            for (String item : cleaned) {
                String[] parts = item.split(",", 2);
                String property = parts[0].trim();
                if (!allowedProps.contains(property)) continue; // ignora lixo tipo ["string"]
                String direction = (parts.length > 1 ? parts[1].trim() : "asc");
                orders.add(new Sort.Order(
                        "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC,
                        property
                ));
            }
            if (!orders.isEmpty()) sortObj = Sort.by(orders);
        }

        PageRequest pageable = PageRequest.of(page != null ? page : 0, size != null ? size : 10, sortObj);

        // 3) Chama o service; se ainda assim vier um sort inválido, faz fallback sem sort
        try {
            return service.listar(pageable);
        } catch (PropertyReferenceException e) {
            // fallback: repete sem ordenação para não estourar 500
            PageRequest fallback = PageRequest.of(page != null ? page : 0, size != null ? size : 10, Sort.unsorted());
            return service.listar(fallback);
        }
    }

    // Auxiliar sem paginação
    @GetMapping("/all")
    public List<LivroResponse> listarTudo() {
        return service.listar(Pageable.unpaged()).getContent();
    }

    @GetMapping("/{id}")
    public LivroResponse buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public LivroResponse atualizar(@PathVariable Long id, @RequestBody @Valid LivroRequest req) {
        return service.atualizar(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}