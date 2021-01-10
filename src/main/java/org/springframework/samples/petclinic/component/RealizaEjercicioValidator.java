package org.springframework.samples.petclinic.component;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.constant.ValidationConstant;
import org.springframework.samples.petclinic.model.auxiliares.RealizaEjercicioDTO;
import org.springframework.samples.petclinic.service.RealizaEjercicioService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class RealizaEjercicioValidator implements Validator {
	
	private static final Log LOG = LogFactory.getLog(RealizaEjercicioValidator.class);

	@Override
	public boolean supports(Class<?> clazz) {
		return RealizaEjercicioDTO.class.isAssignableFrom(clazz);
	}
	
	@Autowired
	private RealizaEjercicioService realizaEjercicioService;

	@Override
	public void validate(Object target, Errors errors) {
		RealizaEjercicioDTO realizaEjercicio = (RealizaEjercicioDTO) target;
		
		if(StringUtils.isEmpty(realizaEjercicio.getFecha())) {
			LOG.warn(ValidationConstant.VALOR_OBLIGATORIO + ": fecha");
			errors.rejectValue("fecha", "error", ValidationConstant.VALOR_OBLIGATORIO);
		} else if (!(Pattern.matches("^([0-2][0-9]|3[0-1])(\\/|-)(0[1-9]|1[0-2])\\2(\\d{4})$", realizaEjercicio.getFecha()))){
			LOG.warn(ValidationConstant.FECHA_FORMATO_ERRONEO);
			errors.rejectValue("fecha", "error", ValidationConstant.FECHA_FORMATO_ERRONEO);
		} else {
			LocalDate fecha = LocalDate.parse(realizaEjercicio.getFecha(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			if(fecha.isAfter(LocalDate.now())) {
				LOG.warn(ValidationConstant.FECHA_POSTERIOR_ERROR);
				errors.rejectValue("fecha", "error", ValidationConstant.FECHA_POSTERIOR_ERROR);
			}
		}

	}

}
