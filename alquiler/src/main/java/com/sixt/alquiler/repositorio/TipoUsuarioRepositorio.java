package com.sixt.alquiler.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sixt.alquiler.modelo.TipoUsuario;

public interface TipoUsuarioRepositorio extends JpaRepository<TipoUsuario, Integer>{

}
