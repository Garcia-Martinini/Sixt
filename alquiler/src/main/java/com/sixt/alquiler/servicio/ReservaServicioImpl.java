package com.sixt.alquiler.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sixt.alquiler.modelo.Reserva;
import com.sixt.alquiler.repositorio.ReservaRepositorio;

@Service
public class ReservaServicioImpl implements ReservaServicio{
    @Autowired
    private ReservaRepositorio repositorio;
    
    @Override
    public List<Reserva> listarTodasLasReservas() {
        return repositorio.findAll();
    }

    @Override
    public Reserva guardarReserva(Reserva reserva) {
       return repositorio.save(reserva);
    }

    @Override
    public Reserva obtenerReservaPorId(Long id) {
       return null;
    }

    @Override
    public void eliminarReserva(Long id) {
        
    }

    @Override
    public Reserva modificarReserva(Reserva reserva) {
        return null;
    }

}
