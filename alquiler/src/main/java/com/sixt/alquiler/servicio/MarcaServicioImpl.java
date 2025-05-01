package com.sixt.alquiler.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sixt.alquiler.modelo.Marca;
import com.sixt.alquiler.repositorio.MarcaRepositorio;

@Service
public class MarcaServicioImpl implements MarcaServicio {

    @Autowired
    private MarcaRepositorio repositorio;

    @Override
    public List<Marca> listarLasMarcas() {
        return repositorio.findAll();
    }

    @Override
    public void guardarMarca(Marca marca) {
        repositorio.save(marca);
    }

    @Override
    public Marca obtenerMarcaPorIdMarca(Integer id) {
        return repositorio.findById(id).get();
    }

    @Override
    public void modificarMarca(Marca marca) {
        repositorio.save(marca);
    }

    @Override
    public void eliminarMarca(Integer id) {
        repositorio.deleteById(id);
    }

}
