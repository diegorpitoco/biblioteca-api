package br.com.bootcamp360.biblioteca.repositorys;

import br.com.bootcamp360.biblioteca.entitys.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> { }