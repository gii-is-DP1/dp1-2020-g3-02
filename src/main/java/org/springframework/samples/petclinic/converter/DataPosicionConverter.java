package org.springframework.samples.petclinic.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.samples.petclinic.model.auxiliares.DataPosicion;
import org.springframework.samples.petclinic.model.estadisticas.JugadorPartidoStats;
import org.springframework.stereotype.Component;

@Component
public class DataPosicionConverter {
	
	public DataPosicion convertPartidoToPartidoStats(List<JugadorPartidoStats> jugadores) {

		DataPosicion stats = new DataPosicion();
		stats.setData(jugadores);

		return stats;
	}
}
