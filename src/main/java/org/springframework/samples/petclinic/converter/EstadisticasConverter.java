package org.springframework.samples.petclinic.converter;

import org.springframework.samples.petclinic.model.EstadisticaPersonalEntrenamiento;
import org.springframework.samples.petclinic.model.EstadisticaPersonalPartido;
import org.springframework.samples.petclinic.model.estadisticas.EstadisticasDeUnJugadorStats;
import org.springframework.samples.petclinic.model.estadisticas.EstadisticasPersonalesStats;
import org.springframework.stereotype.Component;

@Component
public class EstadisticasConverter {
	
	public EstadisticasPersonalesStats convertEstadisticasToEstadisticasStats(EstadisticaPersonalPartido estadistica) {
		return new EstadisticasPersonalesStats(
				estadistica.getJugador().getId(),
				estadistica.getJugador().getFirstName() +", " + estadistica.getJugador().getLastName(),
				estadistica.getPartido().getFecha(),
				estadistica.getSaquesAcertados(), 
				estadistica.getSaquesTotales(), 
				estadistica.getPorcentajeSaques(), 
				estadistica.getRecepcionesAcertadas(), 
				estadistica.getRecepcionesTotales(), 
				estadistica.getPorcentajeRecepciones(), 
				estadistica.getColocacionesAcertadas(), 
				estadistica.getColocacionesTotales(), 
				estadistica.getPorcentajeColocaciones(), 
				estadistica.getDefensasAcertadas(), 
				estadistica.getDefensasTotales(), 
				estadistica.getPorcentajeDefensas(), 
				estadistica.getBloqueosAcertados(), 
				estadistica.getBloqueosTotales(), 
				estadistica.getPorcentajeBloqueos(), 
				estadistica.getRematesAcertados(), 
				estadistica.getRematesTotales(), 
				estadistica.getPorcentajeRemates(), 
				estadistica.getFintasAcertadas(), 
				estadistica.getFintasTotales(), 
				estadistica.getPorcentajeFintas(), 
				estadistica.getNumAtaquesRapidosAcertados(), 
				estadistica.getNumAtaquesRapidosTotales(), 
				estadistica.getPorcentajeAtaquesRapidos(),
				estadistica.getPartido().getId()
				);
	}
	
	public EstadisticasDeUnJugadorStats convertEstadisticasPersonalesToJugadorStats(EstadisticaPersonalPartido estadisiticas) {
		
		return new EstadisticasDeUnJugadorStats(
				estadisiticas.getId(),
				estadisiticas.getPartido().getFecha(),
				estadisiticas.getPartido().getHora(),
				estadisiticas.getSaquesAcertados(), 
				estadisiticas.getSaquesTotales(), 
				estadisiticas.getPorcentajeSaques(), 
				estadisiticas.getRecepcionesAcertadas(), 
				estadisiticas.getRecepcionesTotales(), 
				estadisiticas.getPorcentajeRecepciones(), 
				estadisiticas.getColocacionesAcertadas(), 
				estadisiticas.getColocacionesTotales(), 
				estadisiticas.getPorcentajeColocaciones(), 
				estadisiticas.getDefensasAcertadas(), 
				estadisiticas.getDefensasTotales(), 
				estadisiticas.getPorcentajeDefensas(), 
				estadisiticas.getBloqueosAcertados(), 
				estadisiticas.getBloqueosTotales(), 
				estadisiticas.getPorcentajeBloqueos(), 
				estadisiticas.getRematesAcertados(), 
				estadisiticas.getRematesTotales(), 
				estadisiticas.getPorcentajeRemates(), 
				estadisiticas.getFintasAcertadas(), 
				estadisiticas.getFintasTotales(), 
				estadisiticas.getPorcentajeFintas(), 
				estadisiticas.getNumAtaquesRapidosAcertados(), 
				estadisiticas.getNumAtaquesRapidosTotales(), 
				estadisiticas.getPorcentajeAtaquesRapidos()
				);
	}
	
	public EstadisticasDeUnJugadorStats convertEstadisticasPersonalesToJugadorStats(EstadisticaPersonalEntrenamiento estadisiticas) {
		
		return new EstadisticasDeUnJugadorStats(
				estadisiticas.getId(),
				estadisiticas.getEntrenamiento().getFecha(),
				estadisiticas.getEntrenamiento().getHora(),
				estadisiticas.getSaquesAcertados(), 
				estadisiticas.getSaquesTotales(), 
				estadisiticas.getPorcentajeSaques(), 
				estadisiticas.getRecepcionesAcertadas(), 
				estadisiticas.getRecepcionesTotales(), 
				estadisiticas.getPorcentajeRecepciones(), 
				estadisiticas.getColocacionesAcertadas(), 
				estadisiticas.getColocacionesTotales(), 
				estadisiticas.getPorcentajeColocaciones(), 
				estadisiticas.getDefensasAcertadas(), 
				estadisiticas.getDefensasTotales(), 
				estadisiticas.getPorcentajeDefensas(), 
				estadisiticas.getBloqueosAcertados(), 
				estadisiticas.getBloqueosTotales(), 
				estadisiticas.getPorcentajeBloqueos(), 
				estadisiticas.getRematesAcertados(), 
				estadisiticas.getRematesTotales(), 
				estadisiticas.getPorcentajeRemates(), 
				estadisiticas.getFintasAcertadas(), 
				estadisiticas.getFintasTotales(), 
				estadisiticas.getPorcentajeFintas(), 
				estadisiticas.getNumAtaquesRapidosAcertados(), 
				estadisiticas.getNumAtaquesRapidosTotales(), 
				estadisiticas.getPorcentajeAtaquesRapidos()
				);
	}
	
}
