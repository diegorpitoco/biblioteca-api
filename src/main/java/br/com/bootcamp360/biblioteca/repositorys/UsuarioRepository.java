package br.com.bootcamp360.biblioteca.repositorys;

import br.com.bootcamp360.biblioteca.entitys.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> { }