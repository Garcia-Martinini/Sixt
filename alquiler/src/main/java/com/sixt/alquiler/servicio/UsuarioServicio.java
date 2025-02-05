package com.sixt.alquiler.servicio;

import java.util.List;

import com.sixt.alquiler.modelo.Usuario;

public interface UsuarioServicio {
    
    public List<Usuario> listartodosLosUsuarios();

    public Usuario guardarUsuario(Usuario usuario);

    public Usuario obtenerUsuarioPorId(Long id);

    public Usuario actualizarUsuario(Usuario usuario);

    public void eliminarUsuarioPorId(Long id);

    public Usuario obtenerUsuarioPorUsuario(String usuario);
}
