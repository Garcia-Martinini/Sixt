package com.sixt.alquiler.controlador;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sixt.alquiler.modelo.Estado;
import com.sixt.alquiler.modelo.Modelo;
import com.sixt.alquiler.modelo.Oficina;
import com.sixt.alquiler.modelo.Reserva;
import com.sixt.alquiler.modelo.Usuario;
import com.sixt.alquiler.modelo.Vehiculo;
import com.sixt.alquiler.servicio.ColorServicio;
import com.sixt.alquiler.servicio.EstadoServicio;
import com.sixt.alquiler.servicio.MarcaServicio;
import com.sixt.alquiler.servicio.ModeloServicio;
import com.sixt.alquiler.servicio.OficinaServicio;
import com.sixt.alquiler.servicio.ReservaServicio;
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

    @Autowired
    private ReservaServicio reservaServicio;



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

     // Recurso que permiten obtener los vehiculos disponibles en una oficina
    @GetMapping("/vehiculosPorOficina")
    @ResponseBody
    public List<Vehiculo> obtenerVehiculosPorOficina(
            @RequestParam("idOficina") int idOficina,
            @RequestParam(value = "fechaInicio", required = false) Date fechaInicio,
            @RequestParam(value = "fechaFin", required = false) Date fechaFin) {
        List<Vehiculo> vehiculosDisponibles = new ArrayList<>();

        for (Vehiculo vehiculo : servicio.listarVehiculosPorOficina(idOficina)) {
            if (reservaServicio.VerificarReservasPorVehiculo(vehiculo.getIdVehiculo(), fechaInicio, fechaFin)==false) {
                vehiculosDisponibles.add(vehiculo);
            }
        }
        return vehiculosDisponibles;
    }
   

      // Recurso que permiten obtener el precio diario de un vehiculo
      @GetMapping("/precioDiario")
      @ResponseBody
      public double obtenerPrecioDiario(@RequestParam("idVehiculo") int idVehiculo) {
          Vehiculo vehiculo = servicio.obtenerVehiculoPorId(idVehiculo);
          return vehiculo.getPrecioDiario();
      }

      // Recurso que permite mostrar todos los vehículos entregados que son para repatriar
    @GetMapping("/ListarVehiculosARepatriar")
    public String mostrarVehiculosARepatriar(@ModelAttribute("usuarioSesion") Usuario usuario, Model modelo) {
        
        List<Vehiculo> vehiculos = servicio.listarVehiculosParaRepatriar();
        modelo.addAttribute("vehiculos", vehiculos);
        
        modelo.addAttribute("vendedor", usuario);

        return "Administrador/Vehiculo/lista_vehiculos_a_repatriar";
    }

    @PostMapping("/retornarVehiculoAOrigen/{id}")
    public String retornarVehiculoAOrigen(@PathVariable Integer id) {
        Vehiculo vehiculoModificado = servicio.obtenerVehiculoPorId(id);
        vehiculoModificado.setUbicacion(vehiculoModificado.getOficina());
        servicio.actualizarVehiculo(vehiculoModificado);
        return "redirect:/ListarVehiculosARepatriar";
    }

    // Recurso que permite recibir vehículos de una reserva
    @PostMapping("/reestablecerVehiculos")
    public String reestablecerVehiculos(@RequestParam("idReserva") Long idReserva, @RequestParam("idOficina") int idOficina,
            RedirectAttributes redirectAttributes) {
        Reserva reserva = reservaServicio.obtenerReservaPorId(idReserva);
        if (reserva == null) {
            redirectAttributes.addFlashAttribute("mensaje", "Reserva no existente.");
            return "/Reservas/BuscarReservaParaReestablecimientoVehiculos";
        }
        if (reserva.getEstado().getIdEstado() == 6) { // Verifico si está finalizada
            redirectAttributes.addFlashAttribute("mensaje", "La Reserva ya ha sido finalizada.");
            return "redirect:/Reservas/BuscarReservaParaReestablecimientoVehiculos";
        }
        Oficina oficina =oficinaServicio.obtenerOficinaPorIdOficina(idOficina);

        for (Vehiculo vehiculo : reserva.getVehiculos()) {
            vehiculo.setUbicacion(oficina); // Cambiar la ubicación del vehículo a la oficina de restitución
            vehiculo.setDisponible(true); // Marcar como disponible
            servicio.guardarVehiculo(vehiculo);
        }
        Estado nuevoEstado = estadoServicio.obtenerEstadoPorIdEstado(6);
        reserva.setEstado(nuevoEstado);
        reservaServicio.modificarReserva(reserva);
        redirectAttributes.addFlashAttribute("oficina", oficina);
        redirectAttributes.addFlashAttribute("reserva", reserva);
        return "redirect:/Reservas/ListarVehiculosReestablecidos";
    }

}
