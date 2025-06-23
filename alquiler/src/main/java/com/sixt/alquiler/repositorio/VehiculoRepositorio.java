package com.sixt.alquiler.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sixt.alquiler.modelo.Vehiculo;

@Repository
public interface VehiculoRepositorio extends JpaRepository<Vehiculo, Integer>{


    Vehiculo findByPatente(String patente);

    @Query("SELECT v FROM Vehiculo v WHERE v.oficina.idOficina = ?1")
    List<Vehiculo> findByVehiculosPorOficina(int idOficina);
    @Query("SELECT v FROM Vehiculo v WHERE v.oficina <> v.ubicacion")
    List<Vehiculo> findByVehiculosParaRepatriar();

}
