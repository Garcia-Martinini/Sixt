package com.sixt.alquiler.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sixt.alquiler.modelo.Oficina;
import com.sixt.alquiler.repositorio.OficinaRepositorio;

@Service
public class OficinaServicioImpl implements OficinaServicio {
    @Autowired
    private OficinaRepositorio repositorio;
    @Override
    public List<Oficina> listarTodasLasOficinas() {
        return repositorio.findAll();
    }

}
