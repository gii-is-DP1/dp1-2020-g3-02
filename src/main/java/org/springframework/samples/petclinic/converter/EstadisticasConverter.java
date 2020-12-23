package org.springframework.samples.petclinic.converter;

import org.springframework.samples.petclinic.model.EstadisticaPersonalPartido;
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
	
}
