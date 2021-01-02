package org.springframework.samples.petclinic.component;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.constant.ValidationConstant;
import org.springframework.samples.petclinic.model.Personales;
import org.springframework.samples.petclinic.service.EntrenadorService;
import org.springframework.samples.petclinic.service.JugadorService;
import org.springframework.samples.petclinic.service.PersonalesService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Component
@NoArgsConstructor
@AllArgsConstructor
public class PersonalesValidator implements Validator {
	
	private static final Log LOG = LogFactory.getLog(PersonalesValidator.class);
	
	@Autowired
	private PersonalesService personalesService;
	
	@Autowired
	private JugadorService jugadorService;
	
	@Autowired
	private EntrenadorService entrenadorService;
	
	@Override
	public void validate(Object target, Errors errors) {
		Personales personales = (Personales) target;


		//Propietario validation
		if ( jugadorService.findByFirstName(personales.getPropietario())==null ||
				entrenadorService.findByFirstName(personales.getPropietario())==null) {
			LOG.warn(ValidationConstant.PROPIETARIO_ERROR);
			errors.rejectValue("propietario", "error",ValidationConstant.PROPIETARIO_ERROR);
		}
		
	}
	@Override
	public boolean supports(Class<?> clazz) {
		return Personales.class.isAssignableFrom(clazz);
	}
}
