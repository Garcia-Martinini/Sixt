package com.sixt.alquiler.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sixt.alquiler.modelo.Oficina;

@Repository
public interface OficinaRepositorio extends JpaRepository<Oficina,Integer>{

}
