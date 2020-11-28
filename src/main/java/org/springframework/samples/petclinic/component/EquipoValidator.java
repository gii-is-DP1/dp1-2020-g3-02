package org.springframework.samples.petclinic.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.service.EquipoService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EquipoValidator implements Validator{

	@Autowired
	private EquipoService equipoService;
	
	@Override
	public void validate(Object target, Errors errors) {
		Equipo equipo = (Equipo) target;
		
				//Categoria validation
				if ( equipo.getCategoria() == null || equipo.getCategoria().length() < 3) {
					errors.rejectValue("categoria", "La categoría debe contener un mínimo de tres carácteres","La categoría debe contener un mínimo de tres carácteres");
				}
				//liga validation
				if ( equipo.getLiga() == null || equipo.getLiga().length() < 3) {
					errors.rejectValue("liga", "El nombre de la liga debe contener un mínimo de tres carácteres","El nombre de la liga debe contener un mínimo de tres carácteres");
				}
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Equipo.class.isAssignableFrom(clazz);
	}
}
