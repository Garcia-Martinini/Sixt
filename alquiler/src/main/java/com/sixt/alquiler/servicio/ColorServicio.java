package com.sixt.alquiler.servicio;

import java.util.List;

import com.sixt.alquiler.modelo.Color;

public interface ColorServicio {
    
    public List<Color> listarLosColores();

    public void guardarColor(Color color);

    public Color obtenerColorPorIdColor(Integer id);

    public void modificarColor(Color color);

    public void eliminarColor(Integer id);
     
}
