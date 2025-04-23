package com.sixt.alquiler.servicio;

import java.util.List;

//import com.sixt.alquiler.modelo.Reserva;
import com.sixt.alquiler.modelo.Vehiculo;

public interface VehiculoServicio {

    List<Vehiculo> listarTodosLosVehiculos();
    List<Vehiculo> listarVehiculosDisponiblesEnOficina(int idOficina, int idEstado);
    Vehiculo obtenerVehiculoPorId(int idVehiculo);
    //List<Reserva> listarReservasPorVehiculo(int idVehiculo);

}
