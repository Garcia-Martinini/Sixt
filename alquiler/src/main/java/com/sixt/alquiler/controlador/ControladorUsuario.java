package com.sixt.alquiler.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sixt.alquiler.modelo.Usuario;
import com.sixt.alquiler.servicio.UsuarioServicio;

@Controller
@SessionAttributes("usuarioSesion")
public class ControladorUsuario {
    @Autowired
    private UsuarioServicio servicio;

    @GetMapping("/")
    public String mostrarLogin(Model modelo, @ModelAttribute("mensaje") String mensaje) {
        Usuario usuario = new Usuario();
        modelo.addAttribute("user", usuario);
        modelo.addAttribute("mensaje", mensaje);
        return "Registro/registro";
    }

    @PostMapping("/login")
    public String validarUsuario(@ModelAttribute("user") Usuario usuario, Model modelo,
            RedirectAttributes redirectAttributes) {
        Usuario usuarioBD = servicio.obtenerUsuarioPorUsuario(usuario.getUsuario());
       

        if (usuarioBD != null && usuarioBD.getContrasenia().equals(usuario.getContrasenia())) {
            if (usuarioBD.getTipoUsuario().getId() == 1) {
                redirectAttributes.addFlashAttribute("usuarioSesion", usuarioBD);
                return "redirect:/gestionAdministrador";
            }
            if (usuarioBD.getTipoUsuario().getId() == 2) {
                redirectAttributes.addFlashAttribute("usuarioSesion", usuarioBD);
                return "redirect:/gestionVendedor";
            }
            if (usuarioBD.getTipoUsuario().getId() == 3) {
                redirectAttributes.addFlashAttribute("usuarioSesion", usuarioBD);
                return "redirect:/gestionCliente";
            }

        }
            redirectAttributes.addFlashAttribute("mensaje", "Usuario o contraseña incorrectos");
            return "redirect:/";
        

    }

    @GetMapping("/gestionAdministrador")
    public String mostrarPanelInicioAdministrador(@ModelAttribute("usuarioSesion") Usuario usuario, Model modelo) {
        modelo.addAttribute("administrador", usuario);
        return "Administrador/administrador";

    }

    @GetMapping("/gestionVendedor")
    public String mostrarPanelInicioVendedor(@ModelAttribute("usuarioSesion") Usuario usuario, Model modelo) {
        modelo.addAttribute("vendedor", usuario);
        return "Vendedor/vendedor";
    }

    @GetMapping("/gestionCliente")
    public String mostrarPanelInicioCliente(@ModelAttribute("usuarioSesion") Usuario usuario, Model modelo) {
        modelo.addAttribute("cliente", usuario);
        return "Cliente/cliente";

    }

     @GetMapping("/logout")
    public String logout(SessionStatus status) {
        status.setComplete(); // Limpiar la sesión
        return "redirect:/";
    }
}
