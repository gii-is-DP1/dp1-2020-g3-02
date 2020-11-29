package org.springframework.samples.petclinic.converter;

import org.springframework.samples.petclinic.model.Partido;
import org.springframework.samples.petclinic.model.ediciones.PartidoEdit;
import org.springframework.samples.petclinic.model.estadisticas.PartidoStats;
import org.springframework.stereotype.Component;

@Component
public class PartidoStatsConverter {
	
public PartidoStats convertPartidoToPartidoStats(Partido partido) {
		
		PartidoStats partidoStats = new PartidoStats();
		
		partidoStats.setJugadores(partido.getJugadores());
		
		return partidoStats;
	}
	

}
