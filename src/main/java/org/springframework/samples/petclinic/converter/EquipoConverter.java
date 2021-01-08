package org.springframework.samples.petclinic.converter;

import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.auxiliares.EquipoCategoria;
import org.springframework.samples.petclinic.model.auxiliares.EquipoTablaEquipos;
import org.springframework.samples.petclinic.model.estadisticas.EquipoStats;
import org.springframework.stereotype.Component;

@Component
public class EquipoConverter {
	
	public EquipoStats convertEquipoToEquipoStats(Equipo equipo) {
		return new EquipoStats(
				equipo.getId(),
				equipo.getCategoria(),
				equipo.getSaquesAcertados(),
				equipo.getSaquesTotales(),
				equipo.getPorcentajeSaques(),
				equipo.getRecepcionesAcertadas(),
				equipo.getRecepcionesTotales(),
				equipo.getPorcentajeRecepciones(),
				equipo.getColocacionesAcertadas(), 
				equipo.getColocacionesTotales(), 
				equipo.getPorcentajeColocaciones(), 
				equipo.getDefensasAcertadas(), 
				equipo.getDefensasTotales(), 
				equipo.getPorcentajeDefensas(), 
				equipo.getBloqueosAcertados(), 
				equipo.getBloqueosTotales(), 
				equipo.getPorcentajeBloqueos(), 
				equipo.getRematesAcertados(), 
				equipo.getRematesTotales(), 
				equipo.getPorcentajeRemates(), 
				equipo.getFintasAcertadas(), 
				equipo.getFintasTotales(), 
				equipo.getPorcentajeFintas(), 
				equipo.getNumAtaquesRapidosAcertados(), 
				equipo.getNumAtaquesRapidosTotales(), 
				equipo.getPorcentajeAtaquesRapidos());
	}
	
	public EquipoTablaEquipos convertEquipoToEquipoTablaEquipo(Equipo equipo) {
		String fed;
		if(equipo.getFederacion()) {
			fed = "Federado";
		}
		else {
			fed = "No federado";
		}
		return new EquipoTablaEquipos(
				equipo.getId(),
				equipo.getSistemaJuego(),
				equipo.getCategoria(),
				equipo.getNumAmarillas(),
				equipo.getNumRojas(),
				equipo.getLiga(),
				fed);
	}
	
	public EquipoCategoria convertEquipoToEquipoCategoria(Equipo equipo) {
		return new EquipoCategoria(equipo.getId(),equipo.getCategoria());
	}

}
