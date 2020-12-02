package org.springframework.samples.petclinic.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.samples.petclinic.enumerate.TipoAutorizacion;
import org.springframework.samples.petclinic.model.Autorizacion;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.auxiliares.DataAutorizacion;
import org.springframework.samples.petclinic.model.auxiliares.JugadorAut;
import org.springframework.samples.petclinic.model.ediciones.JugadorEdit;
import org.springframework.samples.petclinic.model.estadisticas.JugadorStats;
import org.springframework.stereotype.Component;

@Component
public class JugadorConverter {
	
	public JugadorStats convertJugadorToJugadorStats(Jugador jugador) {
		return new JugadorStats(
				jugador.getId(),
				jugador.getFirstName(),
				jugador.getLastName(),
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
				jugador.getPorcentajeAtaquesRapidos()
				);
	}
	
	public JugadorEdit convertJugadorToJugadorEdit(Jugador jugador) {
		return new JugadorEdit(
				jugador.getId(),
				jugador.getFirstName(),
				jugador.getLastName(),
				jugador.getDni(),
				jugador.getDireccion(),
				jugador.getEmail(),
				jugador.getLocalidad(),
				jugador.getFechaNacimiento(),
				jugador.getAltura(),
				jugador.getPeso(),
				jugador.getPosicionPrincipal(),
				jugador.getPosicionSecundaria(),
				jugador.getEstadoActual()
				);
	}
	
	public List<JugadorAut> convertListJugadorToListJugadorAut(List<Jugador> listajug){
		List<JugadorAut> listafin= new ArrayList<JugadorAut>();
		for(int i=0; i<listajug.size(); i++) {
			List<TipoAutorizacion> listaut= new ArrayList<TipoAutorizacion>();
			for(int j=0; j<listajug.get(i).getAutorizacion().size(); j++) {
				listaut.add(listajug.get(i).getAutorizacion().get(j).getTipoAutorizacion());
			};
			JugadorAut jugador= new JugadorAut(listajug.get(i).getId(), listajug.get(i).getFirstName(),
					listajug.get(i).getLastName(), listaut);
			listafin.add(jugador);
			
		}
		return listafin;
	}
	
	    public DataAutorizacion convertListJugadoresAutorizaciones(List<JugadorAut> jugadores) {

	        DataAutorizacion dataaut = new DataAutorizacion();

	        dataaut.setData(jugadores);


	        return dataaut;
	    }
}
