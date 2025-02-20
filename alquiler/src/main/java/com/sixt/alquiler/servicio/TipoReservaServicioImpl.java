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
    public List<TipoReserva> listarLosTipoReserva() {
        return repositorio.findAll();
    }

    @Override
    public void guardarTipoReserva(TipoReserva tipoReserva) {
        repositorio.save(tipoReserva);
    }

    @Override
    public TipoReserva obtenerTipoReservaPorIdTipoReserva(Long id) {
        return repositorio.findById(id).get();
    }

    @Override
    public void modificarTipoReserva(TipoReserva tipoReserva) {
        repositorio.save(tipoReserva);
    }

    @Override
    public void eliminarTipoReserva(Long id) {
        repositorio.deleteById(id);
    }
}
