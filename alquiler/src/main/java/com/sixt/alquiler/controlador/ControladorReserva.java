package com.sixt.alquiler.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sixt.alquiler.modelo.Cliente;
import com.sixt.alquiler.modelo.Estado;
import com.sixt.alquiler.modelo.Oficina;
import com.sixt.alquiler.modelo.Reserva;
import com.sixt.alquiler.modelo.TipoReserva;
import com.sixt.alquiler.modelo.Usuario;
import com.sixt.alquiler.modelo.Vehiculo;
import com.sixt.alquiler.servicio.ClienteServicio;
import com.sixt.alquiler.servicio.EstadoServicio;
import com.sixt.alquiler.servicio.OficinaServicio;
import com.sixt.alquiler.servicio.ReservaServicio;
import com.sixt.alquiler.servicio.TipoReservaServicio;
import com.sixt.alquiler.servicio.VehiculoServicio;

@Controller
@SessionAttributes("usuarioSesion")
public class ControladorReserva {

    @Autowired
    private ReservaServicio servicioReserva;

    @Autowired
    private EstadoServicio servicioEstado;

    @Autowired
    private TipoReservaServicio servicioTipoReserva;
    @Autowired
    private OficinaServicio servicioOficina;
    @Autowired
    private VehiculoServicio servicioVehiculo;
    @Autowired
    private ClienteServicio servicioCliente;

    // Recurso que permite mostrar el formulario para crear una nueva reserva
    @GetMapping("/Reservas/NuevaReserva")
    public String mostrarFormCrearReserva(@ModelAttribute("usuarioSesion") Usuario usuario, Model modelo) {

        Reserva reserva = new Reserva();
        List<TipoReserva> tiposReserva = servicioTipoReserva.listarLosTipoReserva();
        List<Oficina> oficinas = servicioOficina.listarLasOficinas();
        modelo.addAttribute("reserva", reserva);
        modelo.addAttribute("oficinas", oficinas);
        modelo.addAttribute("tiposReserva", tiposReserva);
        modelo.addAttribute("vendedor", usuario);
        return "Vendedor/alta_reserva";
    }

    // Recurso que permite guardar una nueva reserva
    @PostMapping("/guardarReserva")
    public String guardarReserva(@ModelAttribute("reserva") Reserva reserva, RedirectAttributes redirectAttributes) {

        Reserva reservaNueva = new Reserva();
        Estado estado = servicioEstado.obtenerEstadoPorIdEstado(5); // Estado "En Proceso"

        reservaNueva.setEstado(estado);
        reservaNueva.setTipoReserva(reserva.getTipoReserva());
        reservaNueva.setCliente(reserva.getCliente());
        reservaNueva.setFechaInicio(reserva.getFechaInicio());
        reservaNueva.setFechaFin(reserva.getFechaFin());
        reservaNueva.setVehiculos(reserva.getVehiculos());
        reservaNueva.setPrecioTotal(reserva.getPrecioTotal());
        reservaNueva.setDiasTotales(reserva.getDiasTotales());
        reservaNueva.setOficinaOrigen(reserva.getOficinaOrigen());
        servicioReserva.guardarReserva(reservaNueva);
        redirectAttributes.addFlashAttribute("usuarioCliente", reserva.getCliente().getUsuario());
        return "redirect:/Reservas/mostrarReservasCliente";
    }

    @GetMapping("/Reservas/mostrarReservasCliente")
    public String mostrarConjuntoReservasCliente(@ModelAttribute("usuarioSesion") Usuario usuario,@ModelAttribute("usuarioCliente") Usuario usuarioCliente, Model modelo) {

        Cliente cliente = servicioCliente.obtenerClientePorUsuario(usuarioCliente);
        List<Reserva> reservas = servicioReserva.listarReservasPorCliente(cliente);
        modelo.addAttribute("cliente", cliente);
        modelo.addAttribute("reservas", reservas);
        modelo.addAttribute("usuarioSesion", usuario);
        return "Vendedor/mostrar_reservas_cliente";
    }

    // Recurso que permite mostrar el formulario para entregar vehículos de una
    // reserva
    @GetMapping("/Reservas/BuscarReservaParaEntregaVehiculos")
    public String mostrarFormEntregaVehiculos(@ModelAttribute("usuarioSesion") Usuario usuario, Model modelo) {

        Reserva reserva = new Reserva();
        modelo.addAttribute("reserva", reserva);
        modelo.addAttribute("usuarioSesion", usuario);

        return "Vendedor/buscar_reserva_entregar";
    }

    // Recurso que permite entregar vehículos de una reserva
    @PostMapping("/entregarVehiculos")
    public String entregarVehiculos(@RequestParam("idReserva") Long idReserva, RedirectAttributes redirectAttributes) {
        Reserva reserva = servicioReserva.obtenerReservaPorId(idReserva);
        if (reserva == null) {
            redirectAttributes.addFlashAttribute("mensaje", "Reserva no existente.");
            return "redirect:/Reservas/BuscarReservaParaEntregaVehiculos";
        }

        for (Vehiculo vehiculo : reserva.getVehiculos()) {
            if (vehiculo.getDisponible() == false) {
                redirectAttributes.addFlashAttribute("mensaje", "El o los vehículos ya han sido entregados.");
                return "redirect:/Reservas/BuscarReservaParaEntregaVehiculos";
            }

            vehiculo.setDisponible(false); // Marcar como no disponible
            servicioVehiculo.guardarVehiculo(vehiculo);
        }
        redirectAttributes.addFlashAttribute("reserva", reserva);
        return "redirect:/Reservas/ListarVehiculosEntregados";
    }

    // Recurso que permite mostrar los vehículos entregados de una reserva
    @GetMapping("/Reservas/ListarVehiculosEntregados")
    public String mostrarVehiculosEntregados(@ModelAttribute("usuarioSesion") Usuario usuario,
            @ModelAttribute("reserva") Reserva reserva, Model modelo) {
        List<Vehiculo> vehiculos = reserva.getVehiculos();
        modelo.addAttribute("vehiculos", vehiculos);
        modelo.addAttribute("reserva", reserva);
        modelo.addAttribute("vendedor", usuario);

        return "Vendedor/lista_vehiculos_entregados";
    }

    // Recurso que permite mostrar el formulario para restituir vehículos de una
    // reserva
    @GetMapping("/Reservas/BuscarReservaParaReestablecimientoVehiculos")
    public String mostrarFormReestablecimientoVehiculos(@ModelAttribute("usuarioSesion") Usuario usuario,
            Model modelo) {
        List<Oficina> oficinas = servicioOficina.listarLasOficinas();
        Reserva reserva = new Reserva();
        modelo.addAttribute("oficinas", oficinas);
        modelo.addAttribute("reserva", reserva);
        modelo.addAttribute("vendedor", usuario);

        return "Vendedor/buscar_reserva_reestablecer";
    }

    // Recurso que permite mostrar los vehículos entregados de una reserva
    @GetMapping("/Reservas/ListarVehiculosReestablecidos")
    public String mostrarVehiculosReestablecidos(@ModelAttribute("oficina") Oficina oficina, 
            @ModelAttribute("usuarioSesion") Usuario usuario,
            @ModelAttribute("reserva") Reserva reserva, Model modelo) {
        List<Vehiculo> vehiculos = reserva.getVehiculos();
        modelo.addAttribute("oficina", oficina);
        modelo.addAttribute("vehiculos", vehiculos);
        modelo.addAttribute("reserva", reserva);
        modelo.addAttribute("vendedor", usuario);

        return "Vendedor/lista_vehiculos_reestablecidos";
    }

     // Recurso que permite mostrar las reservas del cliente asociado al usuario en sesión
    @GetMapping("/Reservas/listadoReservasCliente")
    public String mostrarReservasCliente(@ModelAttribute("usuarioSesion") Usuario usuario, Model modelo) {
        Cliente cliente = servicioCliente.obtenerClientePorUsuario(usuario);
        List<Reserva> reservas = servicioReserva.listarReservasPorCliente(cliente);
        
        modelo.addAttribute("reservas", reservas);
        modelo.addAttribute("usuarioSesion", usuario);

        return "Cliente/listar_reservas_cliente";
    }
}
