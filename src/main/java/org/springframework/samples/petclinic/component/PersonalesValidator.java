package org.springframework.samples.petclinic.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Personales;
import org.springframework.samples.petclinic.service.PersonalesService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonalesValidator implements Validator{
	
	@Autowired
	private PersonalesService personalesService;

	@Override
	public void validate(Object target, Errors errors) {
		Personales personales = (Personales) target;


		//Propietario validation
		if ( personales.getPropietario()==null ) {
			errors.rejectValue("stock", "El propietario no puede ser nulo","El propietario no puede ser nulo");
		}
		
	}
	@Override
	public boolean supports(Class<?> clazz) {
		return Personales.class.isAssignableFrom(clazz);
	}
}
