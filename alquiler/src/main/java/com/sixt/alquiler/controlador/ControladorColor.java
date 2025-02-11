package com.sixt.alquiler.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.sixt.alquiler.modelo.Color;
import com.sixt.alquiler.servicio.ColorServicio;

@Controller
public class ControladorColor {

    @Autowired
    private ColorServicio servicio;

    @GetMapping("/listarABM_color")
    public String listarLosColores(Model modelo) {
        modelo.addAttribute("colores", servicio.listarLosColores());
        return "Administrador/ABM/color/ABM_color";
    }

    @GetMapping("/nuevoColor")
    public String nuevoColorFormulario(Model modelo) {
        Color color = new Color();
        modelo.addAttribute("color", color);
        return "Administrador/ABM/color/nuevo_color";
    }

    @PostMapping("/guardarColor")
    public String guardarColor(@ModelAttribute("color") Color color) {
        servicio.guardarColor(color);
        return "redirect:/listarABM_color";
    }

    @GetMapping("/modificarColor")
    public String modificarColorFormulario(@ModelAttribute("id") Integer id, Model modelo) {
        Color color = servicio.obtenerColorPorIdColor(id);
        modelo.addAttribute("color", color);
        return "Administrador/ABM/color/modificar_color";
    }
}
