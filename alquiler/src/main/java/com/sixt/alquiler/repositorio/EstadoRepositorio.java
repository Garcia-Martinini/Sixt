package com.sixt.alquiler.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sixt.alquiler.modelo.Estado;

@Repository
public interface EstadoRepositorio extends JpaRepository<Estado,Integer>{

}
