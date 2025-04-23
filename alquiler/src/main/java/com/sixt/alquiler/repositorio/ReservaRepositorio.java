package com.sixt.alquiler.repositorio;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sixt.alquiler.modelo.Reserva;

@Repository
public interface ReservaRepositorio extends JpaRepository<Reserva,Long> {

    List<Reserva> findByVehiculosIdVehiculo(int idVehiculo);  
       
}
