package com.sixt.alquiler.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sixt.alquiler.modelo.Estado;
import com.sixt.alquiler.modelo.Usuario;
import com.sixt.alquiler.servicio.EstadoServicio;

@Controller
@SessionAttributes("usuarioSesion")
public class ControladorEstado {

    @Autowired
    private EstadoServicio servicio;

    @GetMapping("/listarABM_estado")
    public String listarLosEstados(@ModelAttribute("usuarioSesion") Usuario usuario, Model modelo) {
        modelo.addAttribute("estados", servicio.listarLosEstados());
        return "Administrador/ABM/estado/ABM_estado";
    }

    @GetMapping("/nuevoEstado")
    public String nuevoEstadoFormulario(@ModelAttribute("usuarioSesion") Usuario usuario, Model modelo, @ModelAttribute("mensaje") String mensaje) {
        Estado estado = new Estado();
        modelo.addAttribute("estado", estado);
        return "Administrador/ABM/estado/nuevo_estado";
    }

    @PostMapping("/guardarEstado")
    public String guardarEstado(@ModelAttribute("estado") Estado estado, RedirectAttributes redirectAttributes) {
        List<Estado> estados = servicio.listarLosEstados();
        for (Estado e : estados) {
            if (e.getNombreEstado().equalsIgnoreCase(estado.getNombreEstado())) {
                redirectAttributes.addFlashAttribute("mensaje", "El estado que ingresaste ya existe");
                return "redirect:/nuevoEstado";
            }
        }
        servicio.guardarEstado(estado);
        return "redirect:/listarABM_estado";
    }

    @GetMapping("/formularioEstado/{id}")
    public String modificarEstadoFormulario(@ModelAttribute("usuarioSesion") Usuario usuario, @PathVariable Integer id, Model modelo, @ModelAttribute("mensaje") String mensaje) {
        Estado estado = servicio.obtenerEstadoPorIdEstado(id);
        String nombreAnterior = estado.getNombreEstado();
        modelo.addAttribute("estado", estado);
        modelo.addAttribute("nombreAnterior", nombreAnterior);
        return "Administrador/ABM/estado/modificar_estado";
    }

    @PostMapping("/actualizarEstado/{id}")
    public String actualizarEstado(@PathVariable Integer id, @ModelAttribute("estado") Estado estado, RedirectAttributes redirectAttributes) {
        Estado estadoModificado = servicio.obtenerEstadoPorIdEstado(id);
        List<Estado> estados = servicio.listarLosEstados();
        for (Estado e : estados) {
            if (e.getNombreEstado().equalsIgnoreCase(estado.getNombreEstado())) {
                redirectAttributes.addFlashAttribute("mensaje", "El estado que ingresaste ya existe");
                return "redirect:/formularioEstado/" + id;
            }
        }
        estadoModificado.setNombreEstado(estado.getNombreEstado());
        servicio.modificarEstado(estadoModificado);
        return "redirect:/listarABM_estado";
    }

    @GetMapping("/eliminarEstado/{id}")
    public String eliminarEstado(@PathVariable Integer id) {
        servicio.eliminarEstado(id);
        return "redirect:/listarABM_estado";
    }
}
