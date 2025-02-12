package com.sixt.alquiler.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sixt.alquiler.modelo.Cliente;
import com.sixt.alquiler.repositorio.ClienteRepositorio;

@Service
public class ClienteServicioImpl implements ClienteServicio {
    @Autowired
    private ClienteRepositorio repositorio;
    
    @Override
    public List<Cliente> listarTodosLosClientes() {
        return repositorio.findAll();
    }

}
