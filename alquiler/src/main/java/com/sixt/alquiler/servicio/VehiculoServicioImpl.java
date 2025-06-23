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
    public List<Vehiculo> listartodosLosVehiculos() {
        return repositorio.findAll();
    }

    @Override
    public Vehiculo guardarVehiculo(Vehiculo vehiculo) {
        return repositorio.save(vehiculo);
    }

    @Override
    public Vehiculo obtenerVehiculoPorId(int id) {
        return repositorio.findById(id).get();
    }

    @Override
    public Vehiculo actualizarVehiculo(Vehiculo vehiculo) {
        return repositorio.save(vehiculo);
    }

    @Override
    public void eliminarVehiculoPorId(int id) {
        repositorio.deleteById(id);

    }

    @Override
    public Vehiculo obtenerVehiculoPorPatente(String patente) {
        return repositorio.findByPatente(patente);
    }

    @Override
    public List<Vehiculo> listarVehiculosPorOficina(int idOficina) {
        return repositorio.findByVehiculosPorOficina(idOficina);
    }

    @Override
    public List<Vehiculo> listarVehiculosParaRepatriar() {
        
        return repositorio.findByVehiculosParaRepatriar();
    }

}
