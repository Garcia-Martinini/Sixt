package com.sixt.alquiler.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sixt.alquiler.modelo.TipoReserva;
import com.sixt.alquiler.repositorio.TipoReservaRepositorio;

@Service
public class TipoReservaServicioImpl implements TipoReservaServicio {
    @Autowired
    private TipoReservaRepositorio repositorio;
    @Override
    public List<TipoReserva> listarTodosLosTiposReserva() {
        return repositorio.findAll();
    }

}
