package com.sixt.alquiler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sixt.alquiler.controlador.ControladorRegistro;



@SpringBootApplication
public class AlquilerApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(AlquilerApplication.class, args);
	}

	@Autowired
	ControladorRegistro registro = new ControladorRegistro();
	@Override
	public void run(String... args) throws Exception {
		registro.inicio();	
	}

	

	/*@Autowired
	TipoReservaRepositorio repositorio;
    @Override
    public void run(String... args) throws Exception {
		TipoReserva tipoReserva = new TipoReserva("Telefonica");
		repositorio.save(tipoReserva);
		TipoReserva tipoReserva1 = new TipoReserva("Personal");
		repositorio.save(tipoReserva1);
		TipoReserva tipoReserva2 = new TipoReserva("Pagina web");
		repositorio.save(tipoReserva2);
    }*/

}
