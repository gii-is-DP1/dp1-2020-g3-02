package org.springframework.samples.petclinic.component;

import java.time.LocalDate;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.constant.ValidationConstant;
import org.springframework.samples.petclinic.enumerate.TipoPrueba;
import org.springframework.samples.petclinic.model.PruebaCondicionFisica;
import org.springframework.samples.petclinic.service.PruebaCondicionFisicaService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class PruebaCondicionFisicaValidator implements Validator {
	
	private static final Log LOG = LogFactory.getLog(PruebaCondicionFisicaValidator.class);
	
	@Autowired
	private PruebaCondicionFisicaService pruebaCondicionFisicaService;
  
	@Override
	public void validate(Object target, Errors errors) {
		PruebaCondicionFisica pruebaCondicionFisica = (PruebaCondicionFisica) target;
		
		//Fecha
		if (pruebaCondicionFisica.getFecha() == null) {
			LOG.warn(ValidationConstant.VALOR_OBLIGATORIO + ": fecha");
			errors.rejectValue("fecha", "error", ValidationConstant.VALOR_OBLIGATORIO);
		}else if(pruebaCondicionFisica.getFecha().isAfter(LocalDate.now())) {
			LOG.warn(ValidationConstant.FECHA_ANTERIOR_ERROR);
			errors.rejectValue("fecha", "error", ValidationConstant.FECHA_ANTERIOR_ERROR);
		}
		//Dato
		//StringUtils.isEmpty(target)
		if (pruebaCondicionFisica.getDato() == null || pruebaCondicionFisica.getDato() <= 0) {
			LOG.warn(ValidationConstant.DATO_PRUEBA_ERROR);
			errors.rejectValue("dato", "error", ValidationConstant.DATO_PRUEBA_ERROR);
			
		}else if(pruebaCondicionFisica.getTipoPrueba().equals(TipoPrueba.ABDOMINAL) && pruebaCondicionFisica.getDato() != Math.floor(pruebaCondicionFisica.getDato())) {
			LOG.warn(ValidationConstant.VALOR_NUMERICO_ENTERO_ERROR + ": abdominal");
			errors.rejectValue("dato", "error", ValidationConstant.VALOR_NUMERICO_ENTERO_ERROR);
			
		}else if(pruebaCondicionFisica.getTipoPrueba().equals(TipoPrueba.PULSACIONESMINIMAS) && 
				(pruebaCondicionFisica.getDato() != Math.floor(pruebaCondicionFisica.getDato()) ||  pruebaCondicionFisica.getDato() <=30 
				|| pruebaCondicionFisica.getDato() >=200)) {
			LOG.warn(ValidationConstant.PULSACIONES_ERROR);
			errors.rejectValue("dato", "error", ValidationConstant.PULSACIONES_ERROR);
			
		}else if(pruebaCondicionFisica.getTipoPrueba().equals(TipoPrueba.FLEXIBILIDAD) && pruebaCondicionFisica.getDato() >=50) {
			LOG.warn(ValidationConstant.FLEXIBILIDAD_ERROR);
			errors.rejectValue("dato", "error", ValidationConstant.FLEXIBILIDAD_ERROR);
			
		}else if(pruebaCondicionFisica.getTipoPrueba().equals(TipoPrueba.SALTOVERTICAL) && pruebaCondicionFisica.getDato() < pruebaCondicionFisica.getJugador().getAltura()) {
			LOG.warn(ValidationConstant.SALTOVERTICAL_ERROR);
			errors.rejectValue("dato", "error", ValidationConstant.SALTOVERTICAL_ERROR);
		}
		
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return PruebaCondicionFisica.class.isAssignableFrom(clazz);
	}

}
