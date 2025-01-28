package com.sixt.alquiler.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sixt.alquiler.modelo.Cliente;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente,Long>{

}
