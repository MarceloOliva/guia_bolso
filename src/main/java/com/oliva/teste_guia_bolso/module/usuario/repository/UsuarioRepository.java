package com.oliva.teste_guia_bolso.module.usuario.repository;

import com.oliva.teste_guia_bolso.module.usuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
