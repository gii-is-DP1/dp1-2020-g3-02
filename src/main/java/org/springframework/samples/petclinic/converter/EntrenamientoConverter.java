package org.springframework.samples.petclinic.converter;

import java.util.stream.Collectors;

import org.springframework.samples.petclinic.model.Entrenamiento;
import org.springframework.samples.petclinic.model.estadisticas.EntrenamientoStats;
import org.springframework.stereotype.Component;

@Component
public class EntrenamientoConverter {
		
public EntrenamientoStats convertEntrenamientoToEntrenamientoStats(Entrenamiento entrenamiento) {
		
		return new EntrenamientoStats(
				entrenamiento.getId(),
				entrenamiento.getFecha(),
				entrenamiento.getHora(),
				entrenamiento.getEquipo().getCategoria(),
				entrenamiento.getSaquesAcertados(), 
				entrenamiento.getSaquesTotales(), 
				entrenamiento.getPorcentajeSaques(), 
				entrenamiento.getRecepcionesAcertadas(), 
				entrenamiento.getRecepcionesTotales(), 
				entrenamiento.getPorcentajeRecepciones(), 
				entrenamiento.getColocacionesAcertadas(), 
				entrenamiento.getColocacionesTotales(), 
				entrenamiento.getPorcentajeColocaciones(), 
				entrenamiento.getDefensasAcertadas(), 
				entrenamiento.getDefensasTotales(), 
				entrenamiento.getPorcentajeDefensas(), 
				entrenamiento.getBloqueosAcertados(), 
				entrenamiento.getBloqueosTotales(), 
				entrenamiento.getPorcentajeBloqueos(), 
				entrenamiento.getRematesAcertados(), 
				entrenamiento.getRematesTotales(), 
				entrenamiento.getPorcentajeRemates(), 
				entrenamiento.getFintasAcertadas(), 
				entrenamiento.getFintasTotales(), 
				entrenamiento.getPorcentajeFintas(), 
				entrenamiento.getNumAtaquesRapidosAcertados(), 
				entrenamiento.getNumAtaquesRapidosTotales(), 
				entrenamiento.getPorcentajeAtaquesRapidos()
				);
	}

}
