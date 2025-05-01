package com.sixt.alquiler.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sixt.alquiler.modelo.Estado;
import com.sixt.alquiler.repositorio.EstadoRepositorio;

<<<<<<< HEAD

@Service
public class EstadoServicioImpl implements EstadoServicio {
   
=======
@Service
public class EstadoServicioImpl implements EstadoServicio {

>>>>>>> a3263a05705b4e8ee38b15dc3180b83244f444b3
    @Autowired
    private EstadoRepositorio repositorio;

    @Override
<<<<<<< HEAD
    public List<Estado> listarTodosLosEstados() {
        return null;
    }

    @Override
    public Estado guardarEstado(Estado estado) {
        return null;
    }

    @Override
    public Estado obtenerEstadoPorId(int id) {
        
        return repositorio.findById(id).get();
        
    }

    @Override
    public void eliminarEstado(int id) {

    }

    @Override
    public Estado modificarEstado(Estado estado) {
        return null;
    }

   

=======
    public List<Estado> listarLosEstados() {
        return repositorio.findAll();
    }

    @Override
    public void guardarEstado(Estado estado) {
        repositorio.save(estado);
    }

    @Override
    public Estado obtenerEstadoPorIdEstado(Integer id) {
        return repositorio.findById(id).get();
    }

    @Override
    public void modificarEstado(Estado estado) {
        repositorio.save(estado);
    }

    @Override
    public void eliminarEstado(Integer id) {
        repositorio.deleteById(id);
    }

>>>>>>> a3263a05705b4e8ee38b15dc3180b83244f444b3
}
