package org.springframework.samples.petclinic.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.PruebaCondicionFisica;
import org.springframework.samples.petclinic.model.Sustitucion;
import org.springframework.samples.petclinic.service.PruebaCondicionFisicaService;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PruebaCondicionFisicaValidator implements Validator {
	@Autowired
	private PruebaCondicionFisicaService pruebaCondicionFisicaService;
  
	@Override
	public void validate(Object target, Errors errors) {
		PruebaCondicionFisica pruebaCondicionFisica = (PruebaCondicionFisica) target;

		if (pruebaCondicionFisica.getFecha() == null) {
			errors.rejectValue("fechaPruebaCondicionFisica", "La fecha de realización de la prueba física es requerida.", "La fecha de realización de la prueba física es requerida.");
		}
		
		if (pruebaCondicionFisica.getDato() == null) {
			errors.rejectValue("datoPruebaCondicionFisica", "El dato de la prueba física es requerido.", "El dato de la prueba física es requerido.");
		}
		
		//Tipo de prueba
		if ( pruebaCondicionFisica.getTipoPrueba().toString().length() > 30) {
			errors.rejectValue("tipo_prueba", "El tipo de prueba no debe tener más de 30 carácteres","El tipo de prueba no debe tener más de 30 carácteres");
		}
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Sustitucion.class.isAssignableFrom(clazz);
	}

}
