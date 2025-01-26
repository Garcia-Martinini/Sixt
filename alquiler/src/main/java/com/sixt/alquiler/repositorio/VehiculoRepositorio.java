package com.sixt.alquiler.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sixt.alquiler.modelo.Vehiculo;

public interface VehiculoRepositorio extends JpaRepository<Vehiculo, Integer>{

}
