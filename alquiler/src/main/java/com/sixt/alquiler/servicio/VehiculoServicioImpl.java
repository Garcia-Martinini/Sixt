package com.sixt.alquiler.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sixt.alquiler.modelo.Vehiculo;
import com.sixt.alquiler.repositorio.VehiculoRepositorio;

@Service
public class VehiculoServicioImpl implements VehiculoServicio {
    @Autowired
    private VehiculoRepositorio repositorio;
    @Override
    public List<Vehiculo> listarTodosLosVehiculos() {
       return repositorio.findAll();
    }
    @Override
    public List<Vehiculo> listarVehiculosDisponiblesEnOficina(int idOficina, int idEstado) {
        return repositorio.findByOficinaIdOficinaAndEstadoIdEstado(idOficina, idEstado);
    }
    @Override
    public Vehiculo obtenerVehiculoPorId(int idVehiculo) {
        return repositorio.findById(idVehiculo).orElse(null);
    }


}
