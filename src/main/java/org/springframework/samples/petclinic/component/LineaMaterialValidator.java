package org.springframework.samples.petclinic.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.LineaMaterial;
import org.springframework.samples.petclinic.service.LineaMaterialService;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class LineaMaterialValidator implements Validator {
	
	@Autowired
	private LineaMaterialService lineaMaterialService;

	@Override
	public void validate(Object target, Errors errors) {
		LineaMaterial lineaMaterial = (LineaMaterial) target;

		//Cantidad validation
		if ( lineaMaterial.getCantidad()==null || lineaMaterial.getCantidad()<0) {
			errors.rejectValue("cantidad", "La cantidad no debe ser nula ni menor que 0","La cantidad no debe ser nulo ni menor que 0");
		}
		
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return LineaMaterial.class.isAssignableFrom(clazz);
	}
	
}
