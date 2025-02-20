package com.sixt.alquiler.servicio;

import java.util.List;

import com.sixt.alquiler.modelo.TipoUsuario;

public interface TipoUsuarioServicio {

    public List<TipoUsuario> listarLosTipoUsuario();

    public void guardarTipoUsuario(TipoUsuario tipoUsuario);

    public TipoUsuario obtenerTipoUsuarioPorIdTipoUsuario(Integer id);

    public void modificarTipoUsuario(TipoUsuario tipoUsuario);

    public void eliminarTipoUsuario(Integer id);
}
