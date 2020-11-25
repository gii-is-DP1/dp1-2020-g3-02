package org.springframework.samples.petclinic.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.EjercicioIndividual;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.service.EjercicioIndividualService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EjercicioIndividualValidator implements Validator{



	@Autowired
	private EjercicioIndividualService ejercicioIndividualService;

	@Override
	public void validate(Object target, Errors errors) {
		EjercicioIndividual ejercicioIndividual = (EjercicioIndividual) target;


		//Descripcion validation
		if ( ejercicioIndividual.getDescripcion() == null || ejercicioIndividual.getDescripcion().toString().length()>255 ) {
			errors.rejectValue("descripcion", "La descripcion no debe ser nula y tener más de 255 carácteres","La descripcion no debe ser nula y tener más de 255 carácteres");
		}
		//Nombre validation
		if ( ejercicioIndividual.getNombre() == null ||  ejercicioIndividual.getNombre().toString().length()>255) {
			errors.rejectValue("nombre", "El nombre no debe ser nulo y tener más de 255 carácteres","El nombre no debe ser nulo y tener más de 255 carácteres");
		}
		//TipoEjercicio validation
		if ( ejercicioIndividual.getTipoEjercicio() == null) {
			errors.rejectValue("tipo_ejercicio", "El tipo de ejercicio no puede ser nulo","El tipo de ejercicio no puede ser nulo");

		}
	}
	@Override
	public boolean supports(Class<?> clazz) {
		return Jugador.class.isAssignableFrom(clazz);
	}


}