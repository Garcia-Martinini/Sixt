package com.sixt.alquiler.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sixt.alquiler.modelo.Color;
import com.sixt.alquiler.modelo.Usuario;
import com.sixt.alquiler.servicio.ColorServicio;

@Controller
@SessionAttributes("usuarioSesion")
public class ControladorColor {

    @Autowired
    private ColorServicio servicio;

    @GetMapping("/listarABM_color")
    public String listarLosColores(@ModelAttribute("usuarioSesion") Usuario usuario, Model modelo) {
        modelo.addAttribute("colores", servicio.listarLosColores());
        return "Administrador/ABM/color/ABM_color";
    }

    @GetMapping("/nuevoColor")
    public String nuevoColorFormulario(@ModelAttribute("usuarioSesion") Usuario usuario, Model modelo) {
        Color color = new Color();
        modelo.addAttribute("color", color);
        return "Administrador/ABM/color/nuevo_color";
    }

    @PostMapping("/guardarColor")
    public String guardarColor(@ModelAttribute("color") Color color) {
        servicio.guardarColor(color);
        return "redirect:/listarABM_color";
    }

    @GetMapping("/formularioColor/{id}")
    public String modificarColorFormulario(@ModelAttribute("usuarioSesion") Usuario usuario, @PathVariable Integer id, Model modelo) {
        Color color = servicio.obtenerColorPorIdColor(id);
        String nombreAnterior = color.getNombreColor();
        modelo.addAttribute("color", color);
        modelo.addAttribute("nombreAnterior", nombreAnterior);
        return "Administrador/ABM/color/modificar_color";
    }

    @PostMapping("/actualizarColor/{id}")
    public String actualizarColor(@PathVariable Integer id, @ModelAttribute("color") Color color) {
        Color colorModificado = servicio.obtenerColorPorIdColor(id);
        colorModificado.setNombreColor(color.getNombreColor());
        servicio.modificarColor(colorModificado);
        return "redirect:/listarABM_color";
    }

    @GetMapping("/eliminarColor/{id}")
    public String eliminarColor(@PathVariable Integer id) {
        servicio.eliminarColor(id);
        return "redirect:/listarABM_color";
    }
}
