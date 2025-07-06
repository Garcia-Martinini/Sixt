package com.sixt.alquiler.servicio;

import java.util.List;

import com.sixt.alquiler.modelo.Cliente;
import com.sixt.alquiler.modelo.Usuario;

public interface ClienteServicio {
    public List<Cliente> listarTodosLosClientes();
    public Cliente guardarCliente(Cliente cliente);
    public Cliente obtenerClientePorId(Long id);
    public void eliminarCliente(Long id);
    public Cliente modificarCliente(Cliente cliente);
    public Cliente obtenerClientePorIdUsuario(Usuario usuario);
    public Cliente obtenerClientePorUsuario(Usuario usuario);
}
