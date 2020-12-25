package org.springframework.samples.petclinic.model.auxiliares;

import org.springframework.samples.petclinic.enumerate.Posicion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RealizaEjercicioDTO {
	
	private String nombreJugador;
	
	private Posicion posicionJugador;
	
	private String nombreEjercicio;
	
	private String fecha;

}
