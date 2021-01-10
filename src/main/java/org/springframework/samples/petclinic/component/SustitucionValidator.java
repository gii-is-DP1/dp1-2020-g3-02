package org.springframework.samples.petclinic.component;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.constant.ValidationConstant;
import org.springframework.samples.petclinic.model.Sustitucion;
import org.springframework.samples.petclinic.service.SustitucionService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class SustitucionValidator implements Validator {
	
	private static final Log LOG = LogFactory.getLog(SustitucionValidator.class);
	
	@Autowired
	private SustitucionService sustitucionService;
  
	@Override
	public void validate(Object target, Errors errors) {
		Sustitucion sustitucion = (Sustitucion) target;

		if (sustitucion.getMinutoSustitucion() == null || sustitucion.getMinutoSustitucion() < 0) {
			LOG.warn(ValidationConstant.MINUTO_SUSTITUCION_ERROR);
			errors.rejectValue("minutoSustitucion", "error",ValidationConstant.MINUTO_SUSTITUCION_ERROR);
		}
		
		if (sustitucion.getJugadorEntra() == null) {
			LOG.warn(ValidationConstant.VALOR_OBLIGATORIO + ": jugadorEntra");
			errors.rejectValue("jugadorEntra", "error",ValidationConstant.VALOR_OBLIGATORIO);
		}
		
		if (sustitucion.getJugadorSale() == null) {
			LOG.warn(ValidationConstant.VALOR_OBLIGATORIO + ": jugadorSale");
			errors.rejectValue("jugadorSale", "error",ValidationConstant.VALOR_OBLIGATORIO);
		}
		
		if (sustitucion.getPartido() == null) {
			LOG.warn(ValidationConstant.VALOR_OBLIGATORIO + ": partido");
			errors.rejectValue("partido", "error",ValidationConstant.VALOR_OBLIGATORIO);
		}
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Sustitucion.class.isAssignableFrom(clazz);
	}
}
