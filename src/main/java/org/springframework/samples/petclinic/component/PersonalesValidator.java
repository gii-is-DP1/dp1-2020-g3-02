package org.springframework.samples.petclinic.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Personales;
import org.springframework.samples.petclinic.service.EntrenadorService;
import org.springframework.samples.petclinic.service.JugadorService;
import org.springframework.samples.petclinic.service.PersonalesService;
import org.springframework.samples.petclinic.service.impl.JugadorServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonalesValidator implements Validator{
	
	@Autowired
	private PersonalesService personalesService;
	private JugadorService jugadorService;
	private EntrenadorService entrenadorService;
	@Override
	public void validate(Object target, Errors errors) {
		Personales personales = (Personales) target;


		//Propietario validation
		if ( jugadorService.findByFirstName(personales.getPropietario())==null ||
				entrenadorService.findByFirstName(personales.getPropietario())==null) {
			errors.rejectValue("stock", "El propietario debe ser un jugador o un entrenador","El propietario debe ser un jugador o un entrenador");
		}
		
	}
	@Override
	public boolean supports(Class<?> clazz) {
		return Personales.class.isAssignableFrom(clazz);
	}
}
