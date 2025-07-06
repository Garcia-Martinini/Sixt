package com.sixt.alquiler.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sixt.alquiler.modelo.Cliente;
import com.sixt.alquiler.modelo.Usuario;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente,Long>{
    
    Cliente findClienteByUsuario_IdUsuario(Long idUsuario);
    Cliente findClienteByUsuario(Usuario usuario);

}

