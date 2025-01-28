package com.sixt.alquiler.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sixt.alquiler.modelo.Vehiculo;

@Repository
public interface VehiculoRepositorio extends JpaRepository<Vehiculo, Integer>{

}
