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
        return "Registro/registro";
    }

    @PostMapping("/login")
    public String validarUsuario(@ModelAttribute("user") Usuario usuario, Model modelo,
            RedirectAttributes redirectAttributes) {
        Usuario usuarioBD = servicio.obtenerUsuarioPorUsuario(usuario.getUsuario());

        if (usuarioBD != null && usuarioBD.getContrasenia().equals(usuario.getContrasenia())) {

            if(usuarioBD.getEstado().getIdEstado() == 2){
                redirectAttributes.addFlashAttribute("mensaje", "El usuario está anulado");
                return "redirect:/";
            }
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

    @GetMapping("/logout")
    public String logout(SessionStatus status) {
        status.setComplete(); // Limpiar la sesión
        return "redirect:/";
    }

    @GetMapping("/gestionUsuario")
    public String gestionarUsuarios(@ModelAttribute("usuarioSesion") Usuario usuarioSesion, Model modelo) {
        modelo.addAttribute("usuarios", servicio.listartodosLosUsuarios());
        modelo.addAttribute("usuarioSesion", usuarioSesion);
        return "Administrador/Usuario/ABM_usuario";
    }

    @GetMapping("/nuevoUsuario")
    public String nuevoUsuarioFormulario(@ModelAttribute("usuarioSesion") Usuario usuarioSesion, Model modelo,
            @ModelAttribute("mensaje") String mensaje) {
        Usuario usuario1 = new Usuario();
        modelo.addAttribute("usuarioSesion", usuarioSesion);
        modelo.addAttribute("mensaje", mensaje);
        modelo.addAttribute("user", usuario1);
        modelo.addAttribute("tipoUsuarios", tipoUsuarioServicio.listarLosTipoUsuario());
        return "/Administrador/Usuario/nuevo_usuario";
    }

    @PostMapping("/guardarUsuario")
    public String guardarUsuario(@ModelAttribute("user") Usuario usuario,
            RedirectAttributes redirectAttributes) {
        if (servicio.obtenerUsuarioPorUsuario(usuario.getUsuario()) == null) {
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
    public String modificarUsuarioFormulario(@ModelAttribute("usuarioSesion") Usuario usuarioSesion, @PathVariable Long id,
            Model modelo, @ModelAttribute("mensaje") String mensaje) {
        Usuario usuario1 = servicio.obtenerUsuarioPorId(id);
        String usuarioAnterior = usuario1.getUsuario();
        String contraseniaAnterior = usuario1.getContrasenia();
        String tipoUsuarioAnterior = usuario1.getTipoUsuario().getNombreTipoUsuario();
        String estadoAnterior = usuario1.getEstado().getNombreEstado();
        modelo.addAttribute("usuarioSesion", usuarioSesion);
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
    public String actualizarUsuario(@PathVariable Long id, @ModelAttribute("user") Usuario usuario, 
    RedirectAttributes redirectAttributes) {
        Usuario usuarioModificado = servicio.obtenerUsuarioPorId(id);
        List<Usuario> usuarios = servicio.listartodosLosUsuarios();
        usuarios.remove(usuarioModificado);
        if (usuario.getUsuario().isEmpty()) {
                redirectAttributes.addFlashAttribute("mensaje", "No se puede dejar el campo vacio");
                return "redirect:/formularioUsuario/" + id;
            }
        for (Usuario u : usuarios) {
            if (u.getUsuario().equals(usuario.getUsuario())) {
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
