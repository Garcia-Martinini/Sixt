package com.sixt.alquiler.servicio;

import java.util.List;


import com.sixt.alquiler.modelo.Vehiculo;

public interface VehiculoServicio {


    public List<Vehiculo> listartodosLosVehiculos();

    public Vehiculo guardarVehiculo(Vehiculo vehiculo);

    public Vehiculo obtenerVehiculoPorId(int id);

    public Vehiculo actualizarVehiculo(Vehiculo vehiculo);

    public void eliminarVehiculoPorId(int id);

    public Vehiculo obtenerVehiculoPorPatente(String patente);
    
    public List<Vehiculo> listarVehiculosPorOficina(int idOficina);

}
