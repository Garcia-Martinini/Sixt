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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sixt.alquiler.modelo.Usuario;
import com.sixt.alquiler.servicio.EstadoServicio;
import com.sixt.alquiler.servicio.TipoUsuarioServicio;
import com.sixt.alquiler.servicio.UsuarioServicio;

@Controller
@SessionAttributes("usuarioSesion")
public class ControladorUsuario {

    @Autowired
    private UsuarioServicio servicio;

    @Autowired
    private TipoUsuarioServicio tipoUsuarioServicio;

    @Autowired
    private EstadoServicio estadoServicio;

    @GetMapping("/")
    public String mostrarLogin(Model modelo, @ModelAttribute("mensaje") String mensaje) {
        Usuario usuario = new Usuario();
        modelo.addAttribute("user", usuario);
        modelo.addAttribute("mensaje", mensaje);
        return "Registro/registro1";
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

    @GetMapping("/gestionUsuario")
    public String gestionarUsuarios(@ModelAttribute("usuarioSesion") Usuario usuario, Model modelo) {
        modelo.addAttribute("usuarios", servicio.listartodosLosUsuarios());
        return "Administrador/Usuario/ABM_usuario"; 
    }

    @GetMapping("/nuevoUsuario")
    public String nuevoUsuarioFormulario(@ModelAttribute("usuarioSesion") Usuario usuario, Model modelo, @ModelAttribute("mensaje") String mensaje) {
        Usuario usuario1 = new Usuario();
        modelo.addAttribute("mensaje", mensaje);
        modelo.addAttribute("user", usuario1);
        modelo.addAttribute("tipoUsuarios", tipoUsuarioServicio.listarLosTipoUsuario());
        return "/Administrador/Usuario/nuevo_usuario";
    }

    @PostMapping("/guardarUsuario")
    public String guardarUsuario(@ModelAttribute("usuarioSesion") Usuario usuario, RedirectAttributes redirectAttributes) {
        if(servicio.obtenerUsuarioPorUsuario(usuario.getUsuario()) == null){
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setEstado(estadoServicio.obtenerEstadoPorIdEstado(1));
            nuevoUsuario.setUsuario(usuario.getUsuario());
            nuevoUsuario.setContrasenia(usuario.getContrasenia());
            nuevoUsuario.setTipoUsuario(usuario.getTipoUsuario());
            servicio.guardarUsuario(nuevoUsuario);
            return "redirect:/gestionUsuario";
        }
        redirectAttributes.addFlashAttribute("mensaje", "El usuario que ingresaste ya existe");
        return "redirect:/nuevoUsuario";
    }

    @GetMapping("/formularioUsuario/{id}")
    public String modificarUsuarioFormulario(@ModelAttribute("usuarioSesion") Usuario usuario, @PathVariable Long id, Model modelo, @ModelAttribute("mensaje") String mensaje) {
        Usuario usuario1 = servicio.obtenerUsuarioPorId(id);
        String usuarioAnterior = usuario1.getUsuario();
        String contraseniaAnterior = usuario1.getContrasenia();
        String tipoUsuarioAnterior = usuario1.getTipoUsuario().getNombreTipoUsuario();
        String estadoAnterior = usuario1.getEstado().getNombreEstado();
        modelo.addAttribute("user", usuario1);
        modelo.addAttribute("nombreAnterior", usuarioAnterior);
        modelo.addAttribute("contraseniaAnterior", contraseniaAnterior);
        modelo.addAttribute("tipoUsuarioAnterior", tipoUsuarioAnterior);
        modelo.addAttribute("estadoAnterior", estadoAnterior);
        modelo.addAttribute("tipoUsuarios", tipoUsuarioServicio.listarLosTipoUsuario());
        modelo.addAttribute("estados", estadoServicio.listarLosEstados());
        return "Administrador/Usuario/modificar_usuario";
    }

    @PostMapping("/actualizarUsuario/{id}")
    public String actualizarUsuario(@PathVariable Long id, @ModelAttribute("user") Usuario usuario, RedirectAttributes redirectAttributes) {
        Usuario usuarioModificado = servicio.obtenerUsuarioPorId(id);
        List<Usuario> usuarios = servicio.listartodosLosUsuarios();
        for (Usuario u : usuarios) {
            if (u.getUsuario().equalsIgnoreCase(usuario.getUsuario())) {
                redirectAttributes.addFlashAttribute("mensaje", "El usuario que ingresaste ya existe");
                return "redirect:/formularioUsuario/" + id;
            }
        }
        usuarioModificado.setUsuario(usuario.getUsuario());
        usuarioModificado.setContrasenia(usuario.getContrasenia());
        usuarioModificado.setTipoUsuario(usuario.getTipoUsuario());
        usuarioModificado.setEstado(usuario.getEstado());
        servicio.actualizarUsuario(usuarioModificado);
        return "redirect:/gestionUsuario";
    }

    @GetMapping("/eliminarUsuario/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        servicio.eliminarUsuarioPorId(id);
        return "redirect:/gestionUsuario";
    }
}
