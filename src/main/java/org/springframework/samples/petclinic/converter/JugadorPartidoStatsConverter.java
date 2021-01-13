package org.springframework.samples.petclinic.converter;

import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.estadisticas.JugadorPartidoStats;
import org.springframework.stereotype.Component;

@Component
public class JugadorPartidoStatsConverter {
	
public JugadorPartidoStats convertJugadorToJugadorPartidoStats(Jugador jugador) {
		
		JugadorPartidoStats partidoStats = new JugadorPartidoStats();
		
		partidoStats.setFirstName(jugador.getFirstName());
		partidoStats.setLastName(jugador.getLastName());
		partidoStats.setPrincipal(jugador.getPosicionPrincipal());
		partidoStats.setSecundaria(jugador.getPosicionSecundaria());
		partidoStats.setPorcentajeSaques(jugador.getPorcentajeSaques());
		partidoStats.setPorcentajeRecepciones(jugador.getPorcentajeRecepciones());
		partidoStats.setPorcentajeColocaciones(jugador.getPorcentajeColocaciones());
		partidoStats.setPorcentajeRemates(jugador.getPorcentajeRemates());
		partidoStats.setPorcentajeBloqueos(jugador.getPorcentajeBloqueos());
		partidoStats.setPorcentajeFintas(jugador.getPorcentajeFintas());
		partidoStats.setPorcentajeDefensas(jugador.getPorcentajeDefensas());
		partidoStats.setPorcentajeAtaquesRapidos(jugador.getPorcentajeAtaquesRapidos());
		
		return partidoStats;
	}
	

}
