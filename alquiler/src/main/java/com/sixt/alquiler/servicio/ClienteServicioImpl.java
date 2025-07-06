package com.sixt.alquiler.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sixt.alquiler.modelo.Cliente;
import com.sixt.alquiler.modelo.Usuario;
import com.sixt.alquiler.repositorio.ClienteRepositorio;

@Service
public class ClienteServicioImpl implements ClienteServicio {
    @Autowired
    private ClienteRepositorio repositorio;
    
    @Override
    public List<Cliente> listarTodosLosClientes() {
        return repositorio.findAll();
    }
    
    @Override
    public Cliente guardarCliente(Cliente cliente) {
        return repositorio.save(cliente);
    }

    @Override
    public Cliente obtenerClientePorId(Long id) {
        return repositorio.findById(id).orElse(null);
    }

    @Override
    public void eliminarCliente(Long id) {
        repositorio.deleteById(id);
    }

    @Override
    public Cliente modificarCliente(Cliente cliente) {
        return repositorio.save(cliente);
    }

    @Override
    public Cliente obtenerClientePorIdUsuario(Usuario usuario){
        return repositorio.findClienteByUsuario_IdUsuario(usuario.getIdUsuario());
    }

    @Override
    public Cliente obtenerClientePorUsuario(Usuario usuario) {
       return repositorio.findClienteByUsuario(usuario);
    }

    

}
