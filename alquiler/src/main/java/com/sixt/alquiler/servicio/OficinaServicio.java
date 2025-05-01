package com.sixt.alquiler.servicio;

import java.util.List;

import com.sixt.alquiler.modelo.Oficina;

public interface OficinaServicio {
    
    public List<Oficina> listarLasOficinas();

    public void guardarOficina(Oficina oficina);

    public Oficina obtenerOficinaPorIdOficina(Integer id);

    public void modificarOficina(Oficina oficina);

    public void eliminarOficina(Integer id);

}
