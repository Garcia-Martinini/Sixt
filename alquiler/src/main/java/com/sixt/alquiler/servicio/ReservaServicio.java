package com.sixt.alquiler.servicio;

import java.sql.Date;
import java.util.List;

import com.sixt.alquiler.modelo.Reserva;

public interface ReservaServicio {
    public List<Reserva> listarTodasLasReservas();
    public Reserva guardarReserva(Reserva reserva);
    public Reserva obtenerReservaPorId(Long id);
    public void eliminarReserva(Long id);
    public Reserva modificarReserva(Reserva reserva);
    public List<Reserva> listarReservasPorVehiculo(int idVehiculo);
    public Boolean VerificarReservasPorVehiculo(int idVehiculo, Date inicio, Date fin);
    
}
