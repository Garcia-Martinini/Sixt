package com.sixt.alquiler.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sixt.alquiler.modelo.Modelo;

@Repository
public interface ModeloRepositorio extends JpaRepository<Modelo,Integer>{

    List<Modelo> findByMarcaIdMarca(Integer idMarca);
}
