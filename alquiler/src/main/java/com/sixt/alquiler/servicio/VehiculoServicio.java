package com.sixt.alquiler.servicio;

import java.util.List;

<<<<<<< HEAD
//import com.sixt.alquiler.modelo.Reserva;
=======
>>>>>>> a3263a05705b4e8ee38b15dc3180b83244f444b3
import com.sixt.alquiler.modelo.Vehiculo;

public interface VehiculoServicio {

<<<<<<< HEAD
    List<Vehiculo> listarTodosLosVehiculos();
    List<Vehiculo> listarVehiculosDisponiblesEnOficina(int idOficina, int idEstado);
    Vehiculo obtenerVehiculoPorId(int idVehiculo);
    //List<Reserva> listarReservasPorVehiculo(int idVehiculo);
=======
    public List<Vehiculo> listartodosLosVehiculos();

    public Vehiculo guardarVehiculo(Vehiculo vehiculo);

    public Vehiculo obtenerVehiculoPorId(int id);

    public Vehiculo actualizarVehiculo(Vehiculo vehiculo);

    public void eliminarVehiculoPorId(int id);

    public Vehiculo obtenerVehiculoPorPatente(String patente);
>>>>>>> a3263a05705b4e8ee38b15dc3180b83244f444b3

}
