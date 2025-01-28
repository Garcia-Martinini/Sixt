package com.sixt.alquiler.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sixt.alquiler.modelo.TipoReserva;

@Repository
public interface TipoReservaRepositorio extends JpaRepository<TipoReserva,Long>{

}
