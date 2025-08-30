package br.com.bootcamp360.biblioteca.repositorys;

import br.com.bootcamp360.biblioteca.entitys.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {}