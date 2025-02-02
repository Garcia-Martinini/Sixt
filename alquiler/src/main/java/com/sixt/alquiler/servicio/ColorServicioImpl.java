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

}
