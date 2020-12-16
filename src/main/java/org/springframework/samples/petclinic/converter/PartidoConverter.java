package org.springframework.samples.petclinic.converter;

import java.util.stream.Collectors;

import org.springframework.samples.petclinic.model.Partido;
import org.springframework.samples.petclinic.model.auxiliares.PartidoConAsistencia;
import org.springframework.samples.petclinic.model.ediciones.PartidoEdit;
import org.springframework.stereotype.Component;

@Component
public class PartidoConverter {
	
	public PartidoEdit convertPartidoToPartidoEdit(Partido partido) {
		
		PartidoEdit partidoEdit = new PartidoEdit();
		
		partidoEdit.setId(partido.getId());
		partidoEdit.setEquipo(partido.getEquipo().getCategoria());
		partidoEdit.setFecha(partido.getFecha().toString());
		partidoEdit.setHora(partido.getHora());
		return partidoEdit;
	}
	
	public PartidoConAsistencia convertPartidoToPartidoConAsistencia(Partido partido) {
		
		PartidoConAsistencia partidoConAsistencia = new PartidoConAsistencia();
		
		partidoConAsistencia.setId(partido.getId());
		partidoConAsistencia.setEquipo(partido.getEquipo().getCategoria());
		partidoConAsistencia.setFecha(partido.getFecha().toString());
		partidoConAsistencia.setHora(partido.getHora());
		partidoConAsistencia.setAsistencia(partido.getJugadores().stream().map(x->x.getId()).collect(Collectors.toList()));
		return partidoConAsistencia;
	}

}
