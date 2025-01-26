package com.sixt.alquiler.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sixt.alquiler.modelo.TipoReserva;

public interface TipoReservaRepositorio extends JpaRepository<TipoReserva,Long>{

}
