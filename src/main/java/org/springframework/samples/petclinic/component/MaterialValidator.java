package org.springframework.samples.petclinic.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Material;
import org.springframework.samples.petclinic.service.MaterialService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MaterialValidator implements Validator{

	@Autowired
	private MaterialService materialIndividualService;

	@Override
	public void validate(Object target, Errors errors) {
		Material material = (Material) target;

		//Stock validation
		if ( material.getStock()==null ||  material.getStock()<0) {
			errors.rejectValue("stock", "El stock no debe ser nulo ni menor que 0","El stock no debe ser nulo ni menor que 0");
		}
		//Estado Validation
		if ( material.getEstado()== null  ) {
			errors.rejectValue("estado", "El estado no puede ser nulo ","El estado no puede ser nulo");
		}
		//tipo validation
		if ( material.getTipo().toString().length()>255) {
			errors.rejectValue("tipo_material", "El tipo de material no puede tener más de 255 carácteres","El tipo de material no puede tener más de 255 carácteres");

		}
	}
	@Override
	public boolean supports(Class<?> clazz) {
		return Material.class.isAssignableFrom(clazz);
	}

}
