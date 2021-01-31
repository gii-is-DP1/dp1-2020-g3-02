package org.springframework.samples.petclinic.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Personales;
import org.springframework.samples.petclinic.model.auxiliares.PersonalEdit;
import org.springframework.stereotype.Component;

@Component
public class PersonalConverter {
	
	@Autowired
	JugadorConverter jugadorConverter;
	
	public PersonalEdit convertPersonalToPersonalEdit(Personales personal) {
		
		PersonalEdit personalEdit = new PersonalEdit();
		
		personalEdit.setId(personal.getId());
		personalEdit.setJugador(jugadorConverter.convertParcialJugadorToJugadorDTO(personal.getJugador()));
		personalEdit.setPropietario(personal.getPropietario());
		return personalEdit;
	}
}
