package com.sixt.alquiler.servicio;

import java.util.List;

import com.sixt.alquiler.modelo.Estado;

public interface EstadoServicio {
<<<<<<< HEAD
    public List<Estado> listarTodosLosEstados();
    public Estado guardarEstado(Estado estado);
    public Estado obtenerEstadoPorId(int id);
    public void eliminarEstado(int id);
    public Estado modificarEstado(Estado estado);
=======

    public List<Estado> listarLosEstados();

    public void guardarEstado(Estado estado);

    public Estado obtenerEstadoPorIdEstado(Integer id);

    public void modificarEstado(Estado estado);

    public void eliminarEstado(Integer id);
     
>>>>>>> a3263a05705b4e8ee38b15dc3180b83244f444b3
}
