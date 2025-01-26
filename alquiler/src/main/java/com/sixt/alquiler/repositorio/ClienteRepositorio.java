package com.sixt.alquiler.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sixt.alquiler.modelo.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente,Long>{

}
