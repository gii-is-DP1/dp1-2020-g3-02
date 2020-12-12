package org.springframework.samples.petclinic.component;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.enumerate.TipoPrueba;
import org.springframework.samples.petclinic.model.PruebaCondicionFisica;
import org.springframework.samples.petclinic.model.Sustitucion;
import org.springframework.samples.petclinic.service.PruebaCondicionFisicaService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PruebaCondicionFisicaValidator implements Validator {
	@Autowired
	private PruebaCondicionFisicaService pruebaCondicionFisicaService;
  
	@Override
	public void validate(Object target, Errors errors) {
		PruebaCondicionFisica pruebaCondicionFisica = (PruebaCondicionFisica) target;
		
		//Fecha
		if (pruebaCondicionFisica.getFecha() == null) {
			errors.rejectValue("fecha", "error", "La fecha de realización de la prueba física es requerida.");
		}else if(pruebaCondicionFisica.getFecha().isAfter(LocalDate.now())) {
			errors.rejectValue("fecha", "error", "La fecha no puede ser posterior a hoy");
		}
		//Dato
		if (pruebaCondicionFisica.getDato() == null || pruebaCondicionFisica.getDato() <= 0) {
			errors.rejectValue("dato", "error", "El dato de la prueba física es requerido y debe ser un número.");
		}
		
		if(pruebaCondicionFisica.getTipoPrueba().equals(TipoPrueba.ABDOMINAL) && pruebaCondicionFisica.getDato() != Math.floor(pruebaCondicionFisica.getDato())) {
			errors.rejectValue("dato", "error", "Debe ser un número entero.");
		}
		
		if(pruebaCondicionFisica.getTipoPrueba().equals(TipoPrueba.PULSACIONESMINIMAS) && 
				(pruebaCondicionFisica.getDato() != Math.floor(pruebaCondicionFisica.getDato()) ||  pruebaCondicionFisica.getDato() <=30 
				|| pruebaCondicionFisica.getDato() >=200)) {
			errors.rejectValue("dato", "error", "Debe ser un número entero entre 30 y 200");
		}
		
		if(pruebaCondicionFisica.getTipoPrueba().equals(TipoPrueba.FLEXIBILIDAD) && pruebaCondicionFisica.getDato() >=50) {
			errors.rejectValue("dato", "error", "Debe ser un número decimal y ser menor a 50.");
		}
		
		if(pruebaCondicionFisica.getTipoPrueba().equals(TipoPrueba.SALTOVERTICAL) && pruebaCondicionFisica.getDato() < pruebaCondicionFisica.getJugador().getAltura()) {
			errors.rejectValue("dato", "error", "No puede ser nunca menor que la altura del jugador");
		}
		
		//Tipo de prueba
		if ( pruebaCondicionFisica.getTipoPrueba().toString().length() > 30) {
			errors.rejectValue("tipo_prueba", "El tipo de prueba no debe tener más de 30 carácteres","El tipo de prueba no debe tener más de 30 carácteres");
		}
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return PruebaCondicionFisica.class.isAssignableFrom(clazz);
	}

}
