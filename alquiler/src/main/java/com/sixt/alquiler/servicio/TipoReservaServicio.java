package com.sixt.alquiler.servicio;

import java.util.List;

import com.sixt.alquiler.modelo.TipoReserva;

public interface TipoReservaServicio {

    public List<TipoReserva> listarLosTipoReserva();

    public void guardarTipoReserva(TipoReserva tipoReserva);

    public TipoReserva obtenerTipoReservaPorIdTipoReserva(Long id);

    public void modificarTipoReserva(TipoReserva tipoReserva);

    public void eliminarTipoReserva(Long id);

}
