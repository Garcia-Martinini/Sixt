package com.sixt.alquiler.servicio;

import java.util.List;

import com.sixt.alquiler.modelo.Estado;

public interface EstadoServicio {

    public List<Estado> listarLosEstados();

    public void guardarEstado(Estado estado);

    public Estado obtenerEstadoPorIdEstado(Integer id);

    public void modificarEstado(Estado estado);

    public void eliminarEstado(Integer id);
     
}
