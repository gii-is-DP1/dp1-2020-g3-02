package org.springframework.samples.petclinic.converter;

import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.Partido;
import org.springframework.samples.petclinic.model.ediciones.PartidoEdit;
import org.springframework.samples.petclinic.model.estadisticas.JugadorPartidoStats;
import org.springframework.stereotype.Component;

@Component
public class JugadorPartidoStatsConverter {
	
public JugadorPartidoStats convertPartidoToPartidoStats(Jugador jugador) {
		
		JugadorPartidoStats partidoStats = new JugadorPartidoStats();
		
		partidoStats.setFirstName(jugador.getFirstName());
		partidoStats.setLastName(jugador.getLastName());
		partidoStats.setPrincipal(jugador.getPosicionPrincipal());
		partidoStats.setSecundaria(jugador.getPosicionSecundaria());
		
		return partidoStats;
	}
	

}
