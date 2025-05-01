package com.sixt.alquiler.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sixt.alquiler.modelo.TipoUsuario;
import com.sixt.alquiler.repositorio.TipoUsuarioRepositorio;

@Service
public class TipoUsuarioServicioImpl implements TipoUsuarioServicio{

    @Autowired
    private TipoUsuarioRepositorio repositorio;

    @Override
    public List<TipoUsuario> listarLosTipoUsuario() {
        return repositorio.findAll();
    }

    @Override
    public void guardarTipoUsuario(TipoUsuario tipoUsuario) {
        repositorio.save(tipoUsuario);
    }

    @Override
    public TipoUsuario obtenerTipoUsuarioPorIdTipoUsuario(Integer id) {
        return repositorio.findById(id).get();
    }

    @Override
    public void modificarTipoUsuario(TipoUsuario tipoUsuario) {
        repositorio.save(tipoUsuario);
    }

    @Override
    public void eliminarTipoUsuario(Integer id) {
        repositorio.deleteById(id);
    }
}
