package com.sixt.alquiler.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sixt.alquiler.modelo.TipoUsuario;

@Repository
public interface TipoUsuarioRepositorio extends JpaRepository<TipoUsuario, Integer>{

}
