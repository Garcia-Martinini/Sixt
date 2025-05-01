package com.sixt.alquiler.controlador;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sixt.alquiler.modelo.Modelo;
import com.sixt.alquiler.modelo.Usuario;
import com.sixt.alquiler.modelo.Vehiculo;
import com.sixt.alquiler.servicio.ColorServicio;
import com.sixt.alquiler.servicio.EstadoServicio;
import com.sixt.alquiler.servicio.MarcaServicio;
import com.sixt.alquiler.servicio.ModeloServicio;
import com.sixt.alquiler.servicio.OficinaServicio;
import com.sixt.alquiler.servicio.VehiculoServicio;

@Controller
@SessionAttributes("usuarioSesion")
public class ControladorVehiculo {

    @Autowired
    private VehiculoServicio servicio;

    @Autowired
    private ColorServicio colorServicio;

    @Autowired
    private OficinaServicio oficinaServicio;

    @Autowired
    private MarcaServicio marcaServicio;

    @Autowired
    private ModeloServicio modeloServicio;

    @Autowired
    private EstadoServicio estadoServicio;


    @GetMapping("/gestionVehiculo")
    public String gestionarUsuarios(@ModelAttribute("usuarioSesion") Usuario usuario, Model modelo) {
        modelo.addAttribute("vehiculos", servicio.listartodosLosVehiculos());
        return "Administrador/Vehiculo/ABM_vehiculo"; 
    }

    @GetMapping("/nuevoVehiculo")
    public String nuevoVehiculoFormulario(@ModelAttribute("usuarioSesion") Usuario usuario, Model modelo, @ModelAttribute("mensaje") String mensaje) {
        Vehiculo vehiculo = new Vehiculo();
        modelo.addAttribute("mensaje", mensaje);
        modelo.addAttribute("vehiculo", vehiculo);
        modelo.addAttribute("colores", colorServicio.listarLosColores());
        modelo.addAttribute("marcas", marcaServicio.listarLasMarcas());
        modelo.addAttribute("modelos", modeloServicio.listarLosModelos());
        modelo.addAttribute("oficinas", oficinaServicio.listarLasOficinas());
        return "/Administrador/Vehiculo/nuevo_vehiculo";
    }

    @PostMapping("/guardarVehiculo")
    public String guardarVehiculo(@ModelAttribute("usuarioSesion") Usuario usuario,@ModelAttribute("vehiculo") Vehiculo vehiculo, RedirectAttributes redirectAttributes) {
        if(servicio.obtenerVehiculoPorPatente(vehiculo.getPatente()) == null){
            if(vehiculo.getPrecioDiario() != 0.0 && vehiculo.getCombustible() != 0){
                Vehiculo nuevoVehiculo = new Vehiculo();
                nuevoVehiculo.setEstado(estadoServicio.obtenerEstadoPorIdEstado(1));
                nuevoVehiculo.setPatente(vehiculo.getPatente());
                nuevoVehiculo.setMarca(vehiculo.getMarca());
                nuevoVehiculo.setModelo(vehiculo.getModelo());
                nuevoVehiculo.setCombustible(vehiculo.getCombustible());
                nuevoVehiculo.setPrecioDiario(vehiculo.getPrecioDiario());
                nuevoVehiculo.setColor(vehiculo.getColor());
                nuevoVehiculo.setOficina(vehiculo.getOficina());
                servicio.guardarVehiculo(nuevoVehiculo);
                return "redirect:/gestionVehiculo";    
            }
                redirectAttributes.addFlashAttribute("mensaje", "Debes seleccionar una opcion valida");
                return "redirect:/nuevoVehiculo";
            
            
        }
        redirectAttributes.addFlashAttribute("mensaje", "El vehiculo que ingresaste ya existe");
        return "redirect:/nuevoVehiculo";
    }

    @GetMapping("/formularioVehiculo/{id}")
    public String modificarVehiculoFormulario(@ModelAttribute("usuarioSesion") Usuario usuario, @PathVariable Integer id, Model modelo) {
        Vehiculo vehiculo = servicio.obtenerVehiculoPorId(id);
        String colorAnterior = vehiculo.getColor().getNombreColor();
        Double precioAnterior = vehiculo.getPrecioDiario();
        String oficinaAnterior = vehiculo.getOficina().getNombreOficina();
        String estadoAnterior = vehiculo.getEstado().getNombreEstado();
        modelo.addAttribute("vehiculo", vehiculo);
        modelo.addAttribute("colorAnterior", colorAnterior);
        modelo.addAttribute("precioAnterior", precioAnterior);
        modelo.addAttribute("oficinaAnterior", oficinaAnterior);
        modelo.addAttribute("estadoAnterior", estadoAnterior);
        modelo.addAttribute("oficinas", oficinaServicio.listarLasOficinas());
        modelo.addAttribute("estados", estadoServicio.listarLosEstados());
        modelo.addAttribute("colores", colorServicio.listarLosColores());
        return "Administrador/Vehiculo/modificar_vehiculo";
    }

    @PostMapping("/actualizarVehiculo/{id}")
    public String actualizarVehiculo(@PathVariable Integer id, @ModelAttribute("vehiculo") Vehiculo vehiculo) {
        Vehiculo vehiculoModificado = servicio.obtenerVehiculoPorId(id);
        vehiculoModificado.setColor(vehiculo.getColor());
        vehiculoModificado.setPrecioDiario(vehiculo.getPrecioDiario());
        vehiculoModificado.setOficina(vehiculo.getOficina());
        vehiculoModificado.setEstado(vehiculo.getEstado());
        servicio.actualizarVehiculo(vehiculoModificado);
        return "redirect:/gestionVehiculo";
    }

    @GetMapping("/eliminarVehiculo/{id}")
    public String eliminarVehiculo(@PathVariable Integer id) {
        servicio.eliminarVehiculoPorId(id);
        return "redirect:/gestionVehiculo";
    }

    @GetMapping("/obtenerModelos/{idMarca}")
    @ResponseBody
    public List<Modelo> obtenerModelosPorMarca(@PathVariable Integer idMarca) {
        return modeloServicio.listarLosModelosPorMarca(idMarca);
    }
}
