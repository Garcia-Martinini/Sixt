package com.sixt.alquiler.servicio;

import java.util.List;

import com.sixt.alquiler.modelo.Marca;

public interface MarcaServicio {
    
    public List<Marca> listarLasMarcas();

    public void guardarMarca(Marca marca);

    public Marca obtenerMarcaPorIdMarca(Integer id);

    public void modificarMarca(Marca marca);

    public void eliminarMarca(Integer id);
     
}
