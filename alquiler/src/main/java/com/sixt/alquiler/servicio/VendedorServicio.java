package com.sixt.alquiler.servicio;

import com.sixt.alquiler.modelo.Vendedor;

public interface VendedorServicio {
    Vendedor obtenerVendedorPorIdUsuario(Long idUsuario);
}
