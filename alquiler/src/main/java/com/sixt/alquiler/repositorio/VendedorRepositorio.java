package com.sixt.alquiler.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sixt.alquiler.modelo.Vendedor;

public interface VendedorRepositorio extends JpaRepository<Vendedor, Long> {

    Vendedor findByUsuarioIdUsuario(Long idUsuario);
    
}
