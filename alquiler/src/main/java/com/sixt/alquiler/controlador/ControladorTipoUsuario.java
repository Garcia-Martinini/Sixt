package com.sixt.alquiler.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sixt.alquiler.modelo.TipoUsuario;
import com.sixt.alquiler.modelo.Usuario;
import com.sixt.alquiler.servicio.TipoUsuarioServicio;

@Controller
@SessionAttributes("usuarioSesion")
public class ControladorTipoUsuario {
    @Autowired
    private TipoUsuarioServicio servicio;

    @GetMapping("/listarABM_tipoUsuario")
    public String listarLosTipoUsuario(@ModelAttribute("usuarioSesion") Usuario usuario, Model modelo) {
        modelo.addAttribute("tipoUsuarios", servicio.listarLosTipoUsuario());
        return "Administrador/ABM/tipoUsuario/ABM_tipoUsuario";
    }

    @GetMapping("/nuevoTipoUsuario")
    public String nuevoTipoUsuarioFormulario(@ModelAttribute("usuarioSesion") Usuario usuario, Model modelo) {
        TipoUsuario tipoUsuario = new TipoUsuario();
        modelo.addAttribute("tipoUsuario", tipoUsuario);
        return "Administrador/ABM/tipoUsuario/nuevo_tipoUsuario";
    }

    @PostMapping("/guardarTipoUsuario")
    public String guardarTipoUsuario(@ModelAttribute("tipoUsuario") TipoUsuario tipoUsuario) {
        servicio.guardarTipoUsuario(tipoUsuario);
        return "redirect:/listarABM_tipoUsuario";
    }

    @GetMapping("/formularioTipoUsuario/{id}")
    public String modificarTipoUsuarioFormulario(@ModelAttribute("usuarioSesion") Usuario usuario, @PathVariable Integer id, Model modelo) {
        TipoUsuario tipoUsuario = servicio.obtenerTipoUsuarioPorIdTipoUsuario(id);
        String nombreAnterior = tipoUsuario.getNombreTipoUsuario();
        modelo.addAttribute("tipoUsuario", tipoUsuario);
        modelo.addAttribute("nombreAnterior", nombreAnterior);
        return "Administrador/ABM/tipoUsuario/modificar_tipoUsuario";
    }

    @PostMapping("/actualizarTipoUsuario/{id}")
    public String actualizarTipoUsuario(@PathVariable Integer id, @ModelAttribute("tipoUsuario") TipoUsuario tipoUsuario) {
        TipoUsuario tipoUsuarioModificado = servicio.obtenerTipoUsuarioPorIdTipoUsuario(id);
        tipoUsuarioModificado.setNombreTipoUsuario(tipoUsuario.getNombreTipoUsuario());
        servicio.modificarTipoUsuario(tipoUsuarioModificado);
        return "redirect:/listarABM_tipoUsuario";
    }

    @GetMapping("/eliminarTipoUsuario/{id}")
    public String eliminarUnTipoUsuario(@PathVariable Integer id) {
        servicio.eliminarTipoUsuario(id);
        return "redirect:/listarABM_tipoUsuario";
    }
}
