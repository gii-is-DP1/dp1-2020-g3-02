package org.springframework.samples.petclinic.converter;

import java.util.ArrayList;
import java.util.List;
import org.springframework.samples.petclinic.enumerate.TipoAutorizacion;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.auxiliares.DataAutorizacion;
import org.springframework.samples.petclinic.model.auxiliares.JugadorAut;
import org.springframework.samples.petclinic.model.auxiliares.JugadorCAP;
import org.springframework.samples.petclinic.model.auxiliares.JugadorDTO;
import org.springframework.samples.petclinic.model.auxiliares.JugadorWithEquipo;
import org.springframework.samples.petclinic.model.auxiliares.JugadoresInEquipo;
import org.springframework.samples.petclinic.model.auxiliares.JugadoresInEquipoSinUser;
import org.springframework.samples.petclinic.model.ediciones.JugadorEdit;
import org.springframework.samples.petclinic.model.estadisticas.JugadorStats;
import org.springframework.stereotype.Component;

@Component
public class JugadorConverter {
	
	public JugadorDTO convertParcialJugadorToJugadorDTO(Jugador jugador) {
		return new JugadorDTO(jugador.getId(), 
				jugador.getFirstName(), 
				jugador.getLastName(), 
				null, 
				jugador.getDni(), 
				jugador.getDireccion(), 
				jugador.getEmail(), 
				jugador.getLocalidad(), 
				jugador.getFechaNacimiento(), 
				jugador.getAltura(), 
				jugador.getPeso(), 
				jugador.getPesoIdeal(), 
				jugador.getImc(), 
				jugador.getPosicionPrincipal(), 
				jugador.getPosicionSecundaria(), 
				jugador.getEstadoActual());
	}
	
	public List<JugadorDTO> convertParcialListJugadorToListJugadorDTO(List<Jugador> jugadores){
		List<JugadorDTO> jugadoresDTO = new ArrayList<JugadorDTO>();
		for(int i=0;i<jugadores.size();i++) {
			JugadorDTO jugadorDTO = convertParcialJugadorToJugadorDTO(jugadores.get(i));
			jugadoresDTO.add(jugadorDTO);
		}
		return jugadoresDTO;
	}
	
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
	
	public List<JugadorWithEquipo> convertListJugadorToListJugadorWithEquipo(List<Jugador> listajugadores){
		List<JugadorWithEquipo> listaJugadorWithEquipos= new ArrayList<JugadorWithEquipo>();
		for(int i=0; i<listajugadores.size(); i++) {
			List<String> equipos= new ArrayList<String>();
			for(int j=0; j<listajugadores.get(i).getEquipos().size(); j++) {
				equipos.add(listajugadores.get(i).getEquipos().get(j).getCategoria());
			};
			JugadorWithEquipo jugadorWithEquipo= new JugadorWithEquipo(listajugadores.get(i).getId(), listajugadores.get(i).getFirstName(),
					listajugadores.get(i).getLastName(), listajugadores.get(i).getDni(),listajugadores.get(i).getDireccion(), listajugadores.get(i).getEmail(),
					listajugadores.get(i).getLocalidad(), listajugadores.get(i).getFechaNacimiento(),listajugadores.get(i).getAltura(), listajugadores.get(i).getPeso(),
					listajugadores.get(i).getPosicionPrincipal(), listajugadores.get(i).getPosicionSecundaria(),listajugadores.get(i).getEstadoActual(), equipos , listajugadores.get(i).getUser());
			
			listaJugadorWithEquipos.add(jugadorWithEquipo);
			
		}
		return listaJugadorWithEquipos;
	}
	
	public JugadoresInEquipo  convertJugadorToJugadorInEquipo(Jugador jugador) {
		
		JugadoresInEquipo jugadorInEquipo= new JugadoresInEquipo(jugador.getId(), jugador.getFirstName(),
				jugador.getLastName(),jugador.getAltura(), jugador.getPeso(), jugador.getPesoIdeal(),
				jugador.getPosicionPrincipal(), jugador.getPosicionSecundaria(),jugador.getImc(), jugador.getUser());
		
		return jugadorInEquipo;
				
	}
	
	public List<JugadoresInEquipo> convertListJugadorToListJugadorInEquipo(List<Jugador> listajugadores){
		List<JugadoresInEquipo> listaJugadoresInEquipo= new ArrayList<JugadoresInEquipo>();
		for(int i=0; i<listajugadores.size(); i++) {
			
			listaJugadoresInEquipo.add(convertJugadorToJugadorInEquipo(listajugadores.get(i)));
			
		}
		return listaJugadoresInEquipo;
	}
	
	public JugadoresInEquipoSinUser  convertJugadorToJugadorInEquipoSinUser(Jugador jugador) {
		
		JugadoresInEquipoSinUser jugadorInEquipo= new JugadoresInEquipoSinUser(jugador.getId(), jugador.getFirstName(),
				jugador.getLastName(),jugador.getAltura(), jugador.getPeso(), jugador.getPesoIdeal(),
				jugador.getPosicionPrincipal(), jugador.getPosicionSecundaria(),jugador.getImc(),jugador.getEmail());
		
		return jugadorInEquipo;
				
	}
	
	public List<JugadoresInEquipoSinUser> convertListJugadorToListJugadorInEquipoSinUser(List<Jugador> listajugadores){
		List<JugadoresInEquipoSinUser> listaJugadoresInEquipo= new ArrayList<JugadoresInEquipoSinUser>();
		for(int i=0; i<listajugadores.size(); i++) {
			
			listaJugadoresInEquipo.add(convertJugadorToJugadorInEquipoSinUser(listajugadores.get(i)));
			
		}
		return listaJugadoresInEquipo;
	}
	
	
    public DataAutorizacion convertListJugadoresAutorizaciones(List<JugadorAut> jugadores) {

        DataAutorizacion dataaut = new DataAutorizacion();

        dataaut.setData(jugadores);


        return dataaut;
    }
    
    public JugadorCAP convertJugadorToJugadorCAP(Jugador j) {
    	String nombre = j.getFirstName() + " " + j.getLastName();
		return new JugadorCAP(j.getId(),nombre);
	}
}
