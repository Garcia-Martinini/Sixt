package com.sixt.alquiler.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sixt.alquiler.modelo.Oficina;
import com.sixt.alquiler.repositorio.OficinaRepositorio;

@Service
public class OficinaServicioImpl implements OficinaServicio{
    
    @Autowired
    private OficinaRepositorio repositorio;

    @Override
    public List<Oficina> listarLasOficinas() {
        return repositorio.findAll();
    }

    @Override
    public void guardarOficina(Oficina oficina) {
        repositorio.save(oficina);
    }

    @Override
    public Oficina obtenerOficinaPorIdOficina(Integer id) {
        return repositorio.findById(id).get();
    }

    @Override
    public void modificarOficina(Oficina oficina) {
        repositorio.save(oficina);
    }

    @Override
    public void eliminarOficina(Integer id) {
        repositorio.deleteById(id);
    }
}
