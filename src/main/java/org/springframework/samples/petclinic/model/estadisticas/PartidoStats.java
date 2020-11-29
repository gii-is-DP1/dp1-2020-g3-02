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
public class PartidoStats {
	
	private List<Jugador> jugadores;

}
