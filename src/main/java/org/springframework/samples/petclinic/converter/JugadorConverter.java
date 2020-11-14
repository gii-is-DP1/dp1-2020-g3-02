package org.springframework.samples.petclinic.converter;

import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.estadisticas.JugadorStats;
import org.springframework.stereotype.Component;

@Component
public class JugadorConverter {
	
	public JugadorStats convertToEstadisticas(Jugador jugador) {
		return new JugadorStats(
				jugador.getId(),
				jugador.getSaquesAcertados(), 
				jugador.getSaquesTotales(), 
				jugador.getPorcentajeSaques(), 
				jugador.getRecepcionesAcertadas(), 
				jugador.getRecepcionesTotales(), 
				jugador.getPorcentajeRecepciones(), 
				jugador.getColocacionesAcertadas(), 
				jugador.getColocacionesTotales(), 
				jugador.getPorcentajeColocaciones(), 
				jugador.getDefensasAcertadas(), 
				jugador.getDefensasTotales(), 
				jugador.getPorcentajeDefensas(), 
				jugador.getBloqueosAcertados(), 
				jugador.getBloqueosTotales(), 
				jugador.getPorcentajeBloqueos(), 
				jugador.getRematesAcertados(), 
				jugador.getRematesTotales(), 
				jugador.getPorcentajeRemates(), 
				jugador.getFintasAcertadas(), 
				jugador.getFintasTotales(), 
				jugador.getPorcentajeFintas(), 
				jugador.getNumAtaquesRapidosAcertados(), 
				jugador.getNumAtaquesRapidosTotales(), 
				jugador.getPorcentajeAtaquesRapidos());
	}

}
