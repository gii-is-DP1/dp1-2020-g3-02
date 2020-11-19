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
		
		//Dni validation
		if (sustitucion.getMinutoSustitucion() == null || sustitucion.getMinutoSustitucion() < 0) {
			errors.rejectValue("minutoSustitucion", "El minuto de sustitución es requerido y ha de ser mayor que 0.","El minuto de sustitución es requerido y ha de ser mayor que 0.");
		}
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Sustitucion.class.isAssignableFrom(clazz);
	}
}
