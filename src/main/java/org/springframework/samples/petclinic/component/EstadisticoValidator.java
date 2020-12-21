package org.springframework.samples.petclinic.component;

import java.time.LocalDate;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.constant.ValidationConstant;
import org.springframework.samples.petclinic.model.Entrenador;
import org.springframework.samples.petclinic.model.Estadistico;
import org.springframework.samples.petclinic.service.EstadisticoService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class EstadisticoValidator implements Validator{
	
	@Autowired
	private EstadisticoService estadisticoService;
	
	@Override
	public void validate(Object target, Errors errors) {
		
		Estadistico estadistico = (Estadistico) target;
		
		//Nombre Validation
		if (StringUtils.isEmpty(estadistico.getFirstName())) {
			errors.rejectValue("firstName", "error", ValidationConstant.FIRSTNAME_ERROR);
		}else if(!Pattern.matches("[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]{2,48}",estadistico.getFirstName())){
			errors.rejectValue("firstName", "error",ValidationConstant.FIRSTNAME_ERROR);
		}
		
		//Apellido validation
		if ( StringUtils.isEmpty(estadistico.getLastName()) ) {
			errors.rejectValue("lastName", "error",ValidationConstant.LASTNAME_ERROR);
		}else if(!Pattern.matches("[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]{2,48}",estadistico.getLastName())){
			errors.rejectValue("lastName", "error",ValidationConstant.LASTNAME_ERROR);
		}
		
		//email validation
		if ( StringUtils.isEmpty(estadistico.getEmail())) {
			errors.rejectValue("email", "error",ValidationConstant.VALOR_OBLIGATORIO);
		}else if(!Pattern.matches("^[a-zñÑA-Z0-9.!#$%&’+/=?^_`{|}~-]+@[a-zñÑA-Z0-9-]+(?:.[a-zA-Z0-9-]+)$",estadistico.getEmail())){
			errors.rejectValue("email", "error",ValidationConstant.EMAIL_FORMATO_ERROR);
		} else {
			Estadistico stat = estadisticoService.findByEmail(estadistico.getEmail());
			if(stat != null && stat.getId() != estadistico.getId()) {
				errors.rejectValue("email", "error",ValidationConstant.EMAIL_YAEXISTE_ERROR);
			}
		}
		
		//fecha nacimiento validation
		try {
			if (estadistico.getFechaNacimiento() == null) {
				errors.rejectValue("fechaNacimiento", "error",ValidationConstant.VALOR_OBLIGATORIO);
			} else if(estadistico.getFechaNacimiento().isAfter(LocalDate.now())) {
				errors.rejectValue("fechaNacimiento", "error",ValidationConstant.FECHA_POSTERIOR_ERROR);
			}
		} catch (Exception e) {
			errors.rejectValue("fechaNacimiento", "error",ValidationConstant.FECHA_FORMATO_ERRONEO_INVERSO);
		}
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Estadistico.class.isAssignableFrom(clazz);
	}
}
