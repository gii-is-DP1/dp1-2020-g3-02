package org.springframework.samples.petclinic.converter;

import java.util.stream.Collectors;

import org.springframework.samples.petclinic.model.Viaje;
import org.springframework.samples.petclinic.model.auxiliares.JugadorPartidoViaje;
import org.springframework.stereotype.Component;

@Component
public class ViajeConverter {
	
	public JugadorPartidoViaje convertViajeToJugadorPartidoViaje(Viaje viaje) {
		JugadorPartidoViaje jugadorPartidoViaje = new JugadorPartidoViaje();
		
		jugadorPartidoViaje.setViajeId(viaje.getId());
		jugadorPartidoViaje.setFecha(viaje.getPartido().getFecha());
		jugadorPartidoViaje.setHora(viaje.getPartido().getHora());
		jugadorPartidoViaje.setHaLlegado(viaje.isHaLlegado());
		jugadorPartidoViaje.setNombre(viaje.getJugador().getFirstName()+", " + viaje.getJugador().getLastName());
		if(viaje.getJugador().getNumCamisetas().stream().filter(x->x.getEquipo().equals(viaje.getPartido().getEquipo())).map(x->x.getNumero()).collect(Collectors.toList()).size()>0) {
			jugadorPartidoViaje.setNumCamiseta(viaje.getJugador().getNumCamisetas().stream().filter(x->x.getEquipo().equals(viaje.getPartido().getEquipo())).map(x->x.getNumero()).collect(Collectors.toList()).get(0));
		}
		
		if(viaje.getAutobus() != null) {
			jugadorPartidoViaje.setAutobusId(viaje.getAutobus().getId());
		}
		
		if(viaje.getPersonal() != null) {
			jugadorPartidoViaje.setPropietario(viaje.getPersonal().getPropietario());
		}else {
			jugadorPartidoViaje.setPropietario(null);
		}
		return jugadorPartidoViaje;
	}
	
}
