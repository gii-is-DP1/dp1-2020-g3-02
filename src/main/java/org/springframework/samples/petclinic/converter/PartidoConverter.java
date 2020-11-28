package org.springframework.samples.petclinic.converter;

import org.springframework.samples.petclinic.model.Partido;
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

}
