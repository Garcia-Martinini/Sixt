package com.sixt.alquiler.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sixt.alquiler.modelo.Modelo;
import com.sixt.alquiler.repositorio.ModeloRepositorio;


@Service
public class ModeloServicioImpl implements ModeloServicio {

    @Autowired
    private ModeloRepositorio repositorio;

    @Override
    public List<Modelo> listarLosModelos() {
        return repositorio.findAll();
    }

    @Override
    public void guardarModelo(Modelo modelo) {
        repositorio.save(modelo);
    }

    @Override
    public Modelo obtenerModeloPorIdModelo(Integer id) {
        return repositorio.findById(id).get();
    }

    @Override
    public void modificarModelo(Modelo modelo) {
        repositorio.save(modelo);
    }

    @Override
    public void eliminarModelo(Integer id) {
        repositorio.deleteById(id);
    }

}
