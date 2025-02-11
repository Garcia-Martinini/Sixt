package com.sixt.alquiler.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sixt.alquiler.modelo.Color;
import com.sixt.alquiler.repositorio.ColorRepositorio;

@Service
public class ColorServicioImpl implements ColorServicio{

    @Autowired
    private ColorRepositorio repositorio;
    
    @Override
    public List<Color> listarLosColores() {
        return repositorio.findAll();
    }

    @Override
    public void guardarColor(Color color) {
        repositorio.save(color);
    }

    @Override
    public Color obtenerColorPorIdColor(Integer id) {
        return repositorio.findById(id).get();
    }

    @Override
    public Color modificarColor(Color color) {
        return repositorio.save(color);
    }

    @Override
    public void eliminarColor(Integer id) {
        repositorio.deleteById(id);
    }
}
