package com.sixt.alquiler.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sixt.alquiler.modelo.Estado;
import com.sixt.alquiler.repositorio.EstadoRepositorio;


@Service
public class EstadoServicioImpl implements EstadoServicio {
   
    @Autowired
    private EstadoRepositorio repositorio;

    @Override
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

   

}
