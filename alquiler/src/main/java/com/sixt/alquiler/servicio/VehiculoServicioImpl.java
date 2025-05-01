package com.sixt.alquiler.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
//import com.sixt.alquiler.modelo.Reserva;
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

   /*  @Override
    public List<Reserva> listarReservasPorVehiculo(int idVehiculo) {
        
        Vehiculo vehiculo = repositorio.findById(idVehiculo).orElse(null);
        return vehiculo.getReservas();
    
    }*/


=======
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
>>>>>>> a3263a05705b4e8ee38b15dc3180b83244f444b3
}
