package com.sixt.alquiler.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sixt.alquiler.modelo.Usuario;
import com.sixt.alquiler.repositorio.UsuarioRepositorio;

@Service
public class UsuarioServicioimpl implements UsuarioServicio {
    @Autowired
    private UsuarioRepositorio repositorio;

    @Override
    public List<Usuario> listartodosLosUsuarios() {
        return repositorio.findAll();
    }

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        return repositorio.save(usuario);
    }

    @Override
    public Usuario obtenerUsuarioPorId(Long id) {
        return repositorio.findById(id).get();
    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) {
        return repositorio.save(usuario);
    }

    @Override
    public void eliminarUsuarioPorId(Long id) {
        repositorio.deleteById(id);

    }

    @Override
    public Usuario obtenerUsuarioPorUsuario(String usuario) {
        return repositorio.findByUsuario(usuario);
    }

}
