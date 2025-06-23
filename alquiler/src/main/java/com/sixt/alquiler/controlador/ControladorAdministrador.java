package com.sixt.alquiler.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sixt.alquiler.modelo.Usuario;

@Controller
@SessionAttributes("usuarioSesion")
public class ControladorAdministrador {
    
    @GetMapping("/gestionAdministrador")
        public String mostrarPanelInicioAdministrador(@ModelAttribute("usuarioSesion") Usuario usuario, Model modelo) {
            modelo.addAttribute("administrador", usuario);
            return "Administrador/administrador1";
        }
}