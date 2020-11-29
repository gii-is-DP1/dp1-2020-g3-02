package org.springframework.samples.petclinic.component;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Estadistico;
import org.springframework.samples.petclinic.service.EstadisticoService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EstadisticoValidator implements Validator{
	
	@Autowired
	private EstadisticoService estadisticoService;
	
	@Override
	public void validate(Object target, Errors errors) {
		
		Estadistico estadistico = (Estadistico) target;
		
		//Nombre Validation
		if ( estadistico.getFirstName() == null || estadistico.getFirstName().length() < 3) {
			errors.rejectValue("firstName", "El nombre es requerido y debe tener más de tres letras","El nombre es requerido y debe tener más de tres letras");
		}
		
		//Apellido validation
		if ( estadistico.getLastName() == null || estadistico.getLastName().length() < 3) {
			errors.rejectValue("lastName", "El apellido es requerido y debe tener más de tres letras","El apellido es requerido y debe tener más de tres letras");
		}
		
		//email validation
		if ( estadistico.getEmail() == null || estadistico.getEmail().length() < 5) {
			errors.rejectValue("email", "El email es requerido y debe tener al menos 5 caracteres","El email es requerido y debe tener al menos 5 caracteres");
		}
				
		//fecha nacimiento validation
		try {
			if (estadistico.getFechaNacimiento() == null || estadistico.getFechaNacimiento().isAfter(LocalDate.now())) {
				errors.rejectValue("fechaNacimiento", "La fecha de nacimiento es requerida y debe ser anterior al día de hoy","La fecha de nacimiento es requerida y debe ser anterior al día de hoy");
			}
		} catch (Exception e) {
			// TODO: handle exception
			errors.rejectValue("fechaNacimiento", "La fecha debe tener el formato requerido(YYYY/MM/DD)","La fecha debe tener el formato requerido(YYYY/MM/DD)");
		}
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Estadistico.class.isAssignableFrom(clazz);
	}
}
