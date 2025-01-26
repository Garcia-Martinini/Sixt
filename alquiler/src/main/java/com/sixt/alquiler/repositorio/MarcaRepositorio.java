package com.sixt.alquiler.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sixt.alquiler.modelo.Marca;

public interface MarcaRepositorio extends JpaRepository<Marca,Integer>{

}
