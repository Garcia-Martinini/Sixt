package com.sixt.alquiler.servicio;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sixt.alquiler.modelo.Cliente;
import com.sixt.alquiler.modelo.Reserva;
import com.sixt.alquiler.repositorio.ReservaRepositorio;

@Service
public class ReservaServicioImpl implements ReservaServicio {
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

    @Override
    public List<Reserva> listarReservasPorVehiculo(int idVehiculo) {
        return repositorio.findByVehiculosIdVehiculo(idVehiculo);
    }

    // Metodo que permiten obtener las reservas por cada vehiculo
    @Override
    public Boolean VerificarReservasPorVehiculo(int idVehiculo, Date inicio, Date fin) {

        Boolean reservado = false;
        List<Reserva> reservas = listarReservasPorVehiculo(idVehiculo);
        if (reservas.isEmpty()) {
            return false;
        } else {
            for (Reserva reserva : reservas) {

                if ((!inicio.before(reserva.getFechaInicio()) || !fin.before(reserva.getFechaInicio()))
                        && (!inicio.after(reserva.getFechaFin()) || !fin.after(reserva.getFechaFin()))) {
                    reservado = true;
                }

            }
        }
        return reservado;
    }

    @Override
    public Boolean VerificarExistenciaReservasPorCliente(Cliente cliente) {
        List<Reserva> reservas = repositorio.findByCliente(cliente);

        if (!(reservas.isEmpty())) {
            for (Reserva reserva : reservas) {
                if (reserva.getEstado().getIdEstado() == 5) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public void eliminarReservasPorCliente(Cliente cliente) {
        
        List<Reserva> reservas = repositorio.findByCliente(cliente);
        if (!(reservas.isEmpty())) {
            for (Reserva reserva : reservas) {
                repositorio.delete(reserva);
            }
        }
    }

}
