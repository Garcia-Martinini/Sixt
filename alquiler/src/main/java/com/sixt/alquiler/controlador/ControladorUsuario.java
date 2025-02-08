package com.sixt.alquiler.controlador;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sixt.alquiler.modelo.TipoUsuario;
import com.sixt.alquiler.modelo.Usuario;
import com.sixt.alquiler.servicio.UsuarioServicio;

@Controller
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
        TipoUsuario tipoUsuario = usuarioBD.getTipoUsuario();

        if (usuarioBD != null && usuarioBD.getContrasenia().equals(usuario.getContrasenia())) {
            if (tipoUsuario.getNombreUsuario().equals("administrador")) {
                return "redirect:/gestionAdministrador";
            }
            if (tipoUsuario.getNombreUsuario().equals("vendedor")) {
                redirectAttributes.addFlashAttribute("vendedor", usuarioBD);
                return "redirect:/gestionVendedor";
            }
            if (tipoUsuario.getNombreUsuario().equals("cliente")) {
                return "redirect:/gestionCliente";
            }

        }
        redirectAttributes.addFlashAttribute("mensaje", "Usuario o contraseña incorrectos");
        return "redirect:/";

    }

    @GetMapping("/gestionAdministrador")
    public String mostrarPanelInicioAdministrador() {
        // Usuario usuario = new Usuario();
        // modelo.addAttribute("user", usuario);
        return "Administrador/administrador";

    }

    @GetMapping("/gestionVendedor")
    public String mostrarPanelInicioVendedor(@ModelAttribute("vendedor") Usuario usuario, Model modelo) {
        modelo.addAttribute("vendedor", usuario);
        return "Vendedor/vendedor";
    }

    @GetMapping("/gestionCliente")
    public String mostrarPanelInicioCliente() {
        // Usuario usuario = new Usuario();
        // modelo.addAttribute("user", usuario);
        return "Cliente/cliente";

    }
}
