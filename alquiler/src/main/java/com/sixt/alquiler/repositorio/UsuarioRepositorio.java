package com.sixt.alquiler.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sixt.alquiler.modelo.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{

}
