package org.springframework.samples.petclinic.model.estadisticas;

import java.util.List;

import org.springframework.samples.petclinic.enumerate.Posicion;
import org.springframework.samples.petclinic.model.Jugador;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class JugadorPartidoStats {
		
	private String firstName;
	private String lastName;
	private Posicion principal;
	private Posicion secundaria;
	private double porcentajeSaques;
	private double porcentajeRecepciones;
	private double porcentajeColocaciones;
	private double porcentajeDefensas;
	private double porcentajeBloqueos;
	private double porcentajeRemates;
	private double porcentajeFintas;
	private double porcentajeAtaquesRapidos;
	
}
