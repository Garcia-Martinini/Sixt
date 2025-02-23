package com.sixt.alquiler.servicio;

import java.util.List;

import com.sixt.alquiler.modelo.Estado;

public interface EstadoServicio {
    public List<Estado> listarTodosLosEstados();
    public Estado guardarEstado(Estado estado);
    public Estado obtenerEstadoPorId(int id);
    public void eliminarEstado(int id);
    public Estado modificarEstado(Estado estado);
}
