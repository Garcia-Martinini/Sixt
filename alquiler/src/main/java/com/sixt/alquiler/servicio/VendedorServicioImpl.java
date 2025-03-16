package com.sixt.alquiler.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sixt.alquiler.modelo.Vendedor;
import com.sixt.alquiler.repositorio.VendedorRepositorio;
@Service
public class VendedorServicioImpl implements VendedorServicio {
    @Autowired
    private VendedorRepositorio repositorio;
    @Override
    public Vendedor obtenerVendedorPorIdUsuario(Long idUsuario) {
        return repositorio.findByUsuarioIdUsuario(idUsuario);
    }

}
