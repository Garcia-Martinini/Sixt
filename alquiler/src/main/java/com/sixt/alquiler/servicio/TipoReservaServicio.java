package com.sixt.alquiler.servicio;

import java.util.List;

import com.sixt.alquiler.modelo.TipoReserva;

public interface TipoReservaServicio {

<<<<<<< HEAD
    List<TipoReserva> listarTodosLosTiposReserva();

=======
    public List<TipoReserva> listarLosTipoReserva();

    public void guardarTipoReserva(TipoReserva tipoReserva);

    public TipoReserva obtenerTipoReservaPorIdTipoReserva(Long id);

    public void modificarTipoReserva(TipoReserva tipoReserva);

    public void eliminarTipoReserva(Long id);
>>>>>>> a3263a05705b4e8ee38b15dc3180b83244f444b3
}
