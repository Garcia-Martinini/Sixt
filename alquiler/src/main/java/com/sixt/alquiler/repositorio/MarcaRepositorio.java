package com.sixt.alquiler.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sixt.alquiler.modelo.Marca;

@Repository
public interface MarcaRepositorio extends JpaRepository<Marca,Integer>{

}
