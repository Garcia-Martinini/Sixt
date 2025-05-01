package com.sixt.alquiler.servicio;

import java.util.List;

import com.sixt.alquiler.modelo.Oficina;

public interface OficinaServicio {
<<<<<<< HEAD
    List<Oficina> listarTodasLasOficinas();
=======
    
    public List<Oficina> listarLasOficinas();

    public void guardarOficina(Oficina oficina);

    public Oficina obtenerOficinaPorIdOficina(Integer id);

    public void modificarOficina(Oficina oficina);

    public void eliminarOficina(Integer id);
>>>>>>> a3263a05705b4e8ee38b15dc3180b83244f444b3
}
