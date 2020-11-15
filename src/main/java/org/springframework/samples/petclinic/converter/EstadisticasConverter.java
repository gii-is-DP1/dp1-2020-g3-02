package org.springframework.samples.petclinic.converter;

import org.springframework.samples.petclinic.model.EstadisticaPersonalPartido;
import org.springframework.samples.petclinic.model.estadisticas.EstadisticasStats;
import org.springframework.stereotype.Component;

@Component
public class EstadisticasConverter {
	
	public EstadisticasStats convertEstadisticasToEstadisticasStats(EstadisticaPersonalPartido estadisticas) {
		return new EstadisticasStats(
				estadisticas.getId(),
				estadisticas.getPartido().getFecha(),
				estadisticas.getPartido().getHora(),
				estadisticas.getSaquesAcertados(), 
				estadisticas.getSaquesTotales(), 
				estadisticas.getPorcentajeSaques(), 
				estadisticas.getRecepcionesAcertadas(), 
				estadisticas.getRecepcionesTotales(), 
				estadisticas.getPorcentajeRecepciones(), 
				estadisticas.getColocacionesAcertadas(), 
				estadisticas.getColocacionesTotales(), 
				estadisticas.getPorcentajeColocaciones(), 
				estadisticas.getDefensasAcertadas(), 
				estadisticas.getDefensasTotales(), 
				estadisticas.getPorcentajeDefensas(), 
				estadisticas.getBloqueosAcertados(), 
				estadisticas.getBloqueosTotales(), 
				estadisticas.getPorcentajeBloqueos(), 
				estadisticas.getRematesAcertados(), 
				estadisticas.getRematesTotales(), 
				estadisticas.getPorcentajeRemates(), 
				estadisticas.getFintasAcertadas(), 
				estadisticas.getFintasTotales(), 
				estadisticas.getPorcentajeFintas(), 
				estadisticas.getNumAtaquesRapidosAcertados(), 
				estadisticas.getNumAtaquesRapidosTotales(), 
				estadisticas.getPorcentajeAtaquesRapidos(),
				estadisticas.getNumFaltasTotales(),
				estadisticas.getNumAmarillas(),
				estadisticas.getNumRojas(),
				estadisticas.getTiempoCalentamiento()
				);
	}
	
}
