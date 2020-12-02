package org.springframework.samples.petclinic.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Sustitucion;
import org.springframework.samples.petclinic.service.SustitucionService;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class SustitucionValidator implements Validator {
	
	@Autowired
	private SustitucionService sustitucionService;
  
	@Override
	public void validate(Object target, Errors errors) {
		Sustitucion sustitucion = (Sustitucion) target;

		if (sustitucion.getMinutoSustitucion() == null || sustitucion.getMinutoSustitucion() < 0) {
			errors.rejectValue("minutoSustitucion", "El minuto de sustitución es requerido y ha de ser mayor que 0.","El minuto de sustitución es requerido y ha de ser mayor que 0.");
		}
		
		if (sustitucion.getJugadorEntra() == null) {
			errors.rejectValue("jugadorSustitucion", "El jugador sustituido es requerido.","El jugador sustituido es requerido.");
		}
		
		if (sustitucion.getJugadorSale() == null) {
			errors.rejectValue("jugadorSustitucion", "El jugador sustituido es requerido.","El jugador sustituido es requerido.");
		}
		
		if (sustitucion.getPartido() == null) {
			errors.rejectValue("partidoSustitucion", "El partido en el que realiza la sustitución es requerido.","El partido en el que realiza la sustitución es requerido.");
		}
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Sustitucion.class.isAssignableFrom(clazz);
	}
}
