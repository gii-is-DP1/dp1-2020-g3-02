package org.springframework.samples.petclinic.converter;

import org.springframework.samples.petclinic.model.PruebaCondicionFisica;
import org.springframework.samples.petclinic.model.auxiliares.PruebasSinJugador;

import org.springframework.stereotype.Component;

@Component
public class PruebaConverter {
	
	public PruebasSinJugador convertPruebaToPruebaSinJugador(PruebaCondicionFisica prueba) {
		
		PruebasSinJugador pruebaSinJugador = new PruebasSinJugador();
		
		pruebaSinJugador.setId(prueba.getId());
		pruebaSinJugador.setDato(prueba.getDato());
		pruebaSinJugador.setFecha(prueba.getFecha().toString());
		pruebaSinJugador.setTipoPrueba(prueba.getTipoPrueba());
		
		return pruebaSinJugador;
	}

}
