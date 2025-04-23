package com.sixt.alquiler.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.sixt.alquiler.modelo.Vehiculo;

@Repository
public interface VehiculoRepositorio extends JpaRepository<Vehiculo, Integer>{
    //@Query("SELECT v FROM Vehiculo v WHERE v.oficina.idOficina = ?1 AND v.estado.idEstado = ?2")//?1 y ?2 son los parametros que se pasan al metodo
    //List<Vehiculo> findByOficinaIdAndEstadoId(int idOficina, int idEstado);
    List<Vehiculo> findByOficinaIdOficinaAndEstadoIdEstado(int idOficina, int idEstado);
    
}
