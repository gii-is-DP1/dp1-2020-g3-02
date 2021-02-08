package org.springframework.samples.petclinic.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.samples.petclinic.model.Partido;
import org.springframework.samples.petclinic.model.auxiliares.PartidoConAsistencia;
import org.springframework.samples.petclinic.model.auxiliares.PartidoPuntos;
import org.springframework.samples.petclinic.model.ediciones.PartidoEdit;
import org.springframework.samples.petclinic.model.estadisticas.PartidoStats;
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
		partidoConAsistencia.setPartidoFinalizado(partido.isPartidoFinalizado());
		partidoConAsistencia.setNumJugadores(partido.getJugadores().size());
		return partidoConAsistencia;
	}
	
public PartidoStats convertPartidoToPartidoStats(Partido partido) {
		
		return new PartidoStats(
				partido.getId(),
				partido.getFecha(),
				partido.getHora(),
				partido.getEquipo().getCategoria(),
				partido.getSaquesAcertados(), 
				partido.getSaquesTotales(), 
				partido.getPorcentajeSaques(), 
				partido.getRecepcionesAcertadas(), 
				partido.getRecepcionesTotales(), 
				partido.getPorcentajeRecepciones(), 
				partido.getColocacionesAcertadas(), 
				partido.getColocacionesTotales(), 
				partido.getPorcentajeColocaciones(), 
				partido.getDefensasAcertadas(), 
				partido.getDefensasTotales(), 
				partido.getPorcentajeDefensas(), 
				partido.getBloqueosAcertados(), 
				partido.getBloqueosTotales(), 
				partido.getPorcentajeBloqueos(), 
				partido.getRematesAcertados(), 
				partido.getRematesTotales(), 
				partido.getPorcentajeRemates(), 
				partido.getFintasAcertadas(), 
				partido.getFintasTotales(), 
				partido.getPorcentajeFintas(), 
				partido.getNumAtaquesRapidosAcertados(), 
				partido.getNumAtaquesRapidosTotales(), 
				partido.getPorcentajeAtaquesRapidos(),
				partido.getTiempo51(),
				partido.getTiempo42(),
				partido.getTiempo62()
				);
	}

public PartidoPuntos convertPartidoToPartidoPuntos(Partido partido) {
	return new PartidoPuntos(
			partido.getFecha(),
			partido.getNumPuntosSet1(),
			partido.getNumPuntosSet2(),
			partido.getNumPuntosSet3(),
			partido.getNumPuntosSet4(),
			partido.getNumPuntosSet5(),
			partido.getNumPuntosTotales()
			);
}

public List<PartidoPuntos> convertListPartidoToListPartidoPuntos(List<Partido> partidos) {
	List<PartidoPuntos> partidospuntos= new ArrayList<PartidoPuntos>();
	for(int i=0;i<partidos.size();i++) {
		partidospuntos.add(convertPartidoToPartidoPuntos(partidos.get(i)));
	}
	return partidospuntos;
}

}
