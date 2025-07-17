package com.sixt.alquiler.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sixt.alquiler.modelo.Cliente;
import com.sixt.alquiler.modelo.Reserva;

@Repository
public interface ReservaRepositorio extends JpaRepository<Reserva, Long> {

    List<Reserva> findByVehiculosIdVehiculo(int idVehiculo);

    @Query("SELECT r FROM Reserva r WHERE r.estado.idEstado = 5 AND r.cliente = ?1")
    List<Reserva> findByClienteConReservaActiva(Cliente cliente);

}
