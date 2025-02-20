package com.sixt.alquiler.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sixt.alquiler.modelo.TipoReserva;
import com.sixt.alquiler.modelo.Usuario;
import com.sixt.alquiler.servicio.TipoReservaServicio;

@Controller
@SessionAttributes("usuarioSesion")
public class ControladorTipoReserva {
    @Autowired
    private TipoReservaServicio servicio;

    @GetMapping("/listarABM_tipoReserva")
    public String listarLosTipoReserva(@ModelAttribute("usuarioSesion") Usuario usuario, Model modelo) {
        modelo.addAttribute("tipoReservas", servicio.listarLosTipoReserva());
        return "Administrador/ABM/tipoReserva/ABM_tipoReserva";
    }

    @GetMapping("/nuevoTipoReserva")
    public String nuevoTipoReservaFormulario(@ModelAttribute("usuarioSesion") Usuario usuario, Model modelo) {
        TipoReserva tipoReserva = new TipoReserva();
        modelo.addAttribute("tipoReserva", tipoReserva);
        return "Administrador/ABM/tipoReserva/nuevo_tipoReserva";
    }

    @PostMapping("/guardarTipoReserva")
    public String guardarTipoReserva(@ModelAttribute("tipoReserva") TipoReserva tipoReserva) {
        servicio.guardarTipoReserva(tipoReserva);
        return "redirect:/listarABM_tipoReserva";
    }

    @GetMapping("/formularioTipoReserva/{id}")
    public String modificarTipoReservaFormulario(@ModelAttribute("usuarioSesion") Usuario usuario, @PathVariable Long id, Model modelo) {
        TipoReserva tipoReserva = servicio.obtenerTipoReservaPorIdTipoReserva(id);
        String nombreAnterior = tipoReserva.getNombreTipoReserva();
        modelo.addAttribute("tipoReserva", tipoReserva);
        modelo.addAttribute("nombreAnterior", nombreAnterior);
        return "Administrador/ABM/tipoReserva/modificar_tipoReserva";
    }

    @PostMapping("/actualizarTipoReserva/{id}")
    public String actualizarTipoReserva(@PathVariable Long id, @ModelAttribute("tipoReserva") TipoReserva tipoReserva) {
        TipoReserva tipoReservaModificado = servicio.obtenerTipoReservaPorIdTipoReserva(id);
        tipoReservaModificado.setNombreTipoReserva(tipoReserva.getNombreTipoReserva());
        servicio.modificarTipoReserva(tipoReservaModificado);
        return "redirect:/listarABM_tipoReserva";
    }

    @GetMapping("/eliminarTipoReserva/{id}")
    public String eliminarTipoReserva(@PathVariable Long id) {
        servicio.eliminarTipoReserva(id);
        return "redirect:/listarABM_tipoReserva";
    }
}
