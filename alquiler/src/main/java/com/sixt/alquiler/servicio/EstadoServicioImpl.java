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

}
