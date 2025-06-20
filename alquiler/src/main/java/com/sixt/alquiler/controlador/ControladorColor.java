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
    public String nuevoColorFormulario(@ModelAttribute("usuarioSesion") Usuario usuario, Model modelo, @ModelAttribute("mensaje") String mensaje) {
        Color color = new Color();
        modelo.addAttribute("color", color);
        return "Administrador/ABM/color/nuevo_color";
    }

    @PostMapping("/guardarColor")
    public String guardarColor(@ModelAttribute("color") Color color, RedirectAttributes redirectAttributes) {
        List<Color> colores = servicio.listarLosColores();
        for (Color c : colores) {
            if (c.getNombreColor().equalsIgnoreCase(color.getNombreColor())) {
                redirectAttributes.addFlashAttribute("mensaje", "El color que ingresaste ya existe");
                return "redirect:/nuevoColor";
            }
        }
        servicio.guardarColor(color);
        return "redirect:/listarABM_color";
    }

    @GetMapping("/formularioColor/{id}")
    public String modificarColorFormulario(@ModelAttribute("usuarioSesion") Usuario usuario, @PathVariable Integer id, Model modelo, @ModelAttribute("mensaje") String mensaje) {
        Color color = servicio.obtenerColorPorIdColor(id);
        String nombreAnterior = color.getNombreColor();
        modelo.addAttribute("color", color);
        modelo.addAttribute("nombreAnterior", nombreAnterior);
        return "Administrador/ABM/color/modificar_color";
    }

    @PostMapping("/actualizarColor/{id}")
    public String actualizarColor(@PathVariable Integer id, @ModelAttribute("color") Color color, RedirectAttributes redirectAttributes) {
        Color colorModificado = servicio.obtenerColorPorIdColor(id);
        List<Color> colores = servicio.listarLosColores();
        if (color.getNombreColor().isEmpty()) {
                redirectAttributes.addFlashAttribute("mensaje", "No se puede dejar el campo vacio");
                return "redirect:/formularioColor/" + id;
            }
        for (Color c : colores) {
            if (c.getNombreColor().equalsIgnoreCase(color.getNombreColor())) {
                redirectAttributes.addFlashAttribute("mensaje", "El color que ingresaste ya existe");
                return "redirect:/formularioColor/" + id;
            }
        }
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
