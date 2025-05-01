package com.sixt.alquiler.servicio;

import java.util.List;

import com.sixt.alquiler.modelo.Modelo;

public interface ModeloServicio {
    
    public List<Modelo> listarLosModelos();

    public void guardarModelo(Modelo modelo);

    public Modelo obtenerModeloPorIdModelo(Integer id);

    public void modificarModelo(Modelo modelo);

    public void eliminarModelo(Integer id);

    public List<Modelo> listarLosModelosPorMarca(Integer idMarca);
     
}
