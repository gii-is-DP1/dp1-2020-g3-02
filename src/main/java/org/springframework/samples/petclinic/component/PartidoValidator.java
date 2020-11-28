package org.springframework.samples.petclinic.component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import org.springframework.samples.petclinic.constant.ValidationConstant;
import org.springframework.samples.petclinic.model.ediciones.PartidoEdit;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PartidoValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return PartidoEdit.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		PartidoEdit partido = (PartidoEdit) target;
		
		if(StringUtils.isEmpty(partido.getFecha())) {
			errors.rejectValue("fecha", "error", ValidationConstant.VALOR_OBLIGATORIO);
		} else if (!(Pattern.matches("^([0-2][0-9]|3[0-1])(\\/|-)(0[1-9]|1[0-2])\\2(\\d{4})$", partido.getFecha()))){
			errors.rejectValue("fecha", "error", ValidationConstant.FECHA_FORMATO_ERRONEO);
		} else {
			LocalDate fecha = LocalDate.parse(partido.getFecha(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			if(fecha.isBefore(LocalDate.now())) {
				errors.rejectValue("fecha", "error", ValidationConstant.FECHA_ANTERIOR_ERROR);
			}
		}
		if(StringUtils.isEmpty(partido.getHora())) {
			errors.rejectValue("hora", "error", ValidationConstant.VALOR_OBLIGATORIO);
		}
	}

}
