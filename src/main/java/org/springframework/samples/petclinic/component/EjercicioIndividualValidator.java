package org.springframework.samples.petclinic.component;

import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.constant.ValidationConstant;
import org.springframework.samples.petclinic.model.EjercicioIndividual;
import org.springframework.samples.petclinic.service.EjercicioIndividualService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EjercicioIndividualValidator implements Validator{
	
	private static final Log LOG = LogFactory.getLog(EjercicioIndividualValidator.class);

	@Autowired
	private EjercicioIndividualService ejercicioIndividualService;

	@Override
	public void validate(Object target, Errors errors) {
		EjercicioIndividual ejercicioIndividual = (EjercicioIndividual) target;


		//Descripcion validation
		if (StringUtils.isEmpty(ejercicioIndividual.getDescripcion())) {
			LOG.warn(ValidationConstant.VALOR_OBLIGATORIO + ": descripción");
			errors.rejectValue("descripcion", "error",ValidationConstant.VALOR_OBLIGATORIO);
		}
		//Nombre validation
		if (StringUtils.isEmpty(ejercicioIndividual.getNombre())) {
			LOG.warn(ValidationConstant.VALOR_OBLIGATORIO + ": nombre");
			errors.rejectValue("nombre", "error",ValidationConstant.VALOR_OBLIGATORIO);
		} else {
			Optional<EjercicioIndividual> ejercicio = ejercicioIndividualService.findByNombre(ejercicioIndividual.getNombre());
			if(!ejercicio.equals(Optional.empty()) && ejercicio.get().getId() != ejercicioIndividual.getId()) {
				LOG.warn(ValidationConstant.EJERCICIOS_NOMBRE_DUPLICADO);
				errors.rejectValue("nombre", "error",ValidationConstant.EJERCICIOS_NOMBRE_DUPLICADO);
			}
		}
	}
	@Override
	public boolean supports(Class<?> clazz) {
		return EjercicioIndividual.class.isAssignableFrom(clazz);
	}


}