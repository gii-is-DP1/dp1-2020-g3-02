package org.springframework.samples.petclinic.component;


import java.time.LocalDate;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.constant.ValidationConstant;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.service.JugadorService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Component
@NoArgsConstructor
@AllArgsConstructor
public class JugadorValidator implements Validator {

	@Autowired
	private JugadorService jugadorService;
  
	@Override
	public void validate(Object target, Errors errors) {
		Jugador jugador = (Jugador) target;
		
		//Dni validation
		if ( StringUtils.isEmpty(jugador.getDni()) || !Pattern.matches("[0-9]{8}[A-Za-z]{1}", jugador.getDni())) {
			errors.rejectValue("dni", "error",ValidationConstant.DNI_ERROR);
		}
		
		//Nombre Validation
		if (StringUtils.isEmpty(jugador.getFirstName())) {
			errors.rejectValue("firstName", "error", ValidationConstant.FIRSTNAME_ERROR);
		}else if(!Pattern.matches("[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]{2,48}",jugador.getFirstName())){
			errors.rejectValue("firstName", "error",ValidationConstant.FIRSTNAME_ERROR);
		}
		
		//Apellido validation
		if ( StringUtils.isEmpty(jugador.getLastName()) ) {
			errors.rejectValue("lastName", "error",ValidationConstant.LASTNAME_ERROR);
		}else if(!Pattern.matches("[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]{2,48}",jugador.getLastName())){
			errors.rejectValue("lastName", "error",ValidationConstant.LASTNAME_ERROR);
		}
		
		//email validation
		if ( StringUtils.isEmpty(jugador.getEmail())) {
			errors.rejectValue("email", "error",ValidationConstant.VALOR_OBLIGATORIO);
		}else if(!Pattern.matches("^[a-zñÑA-Z0-9.!#$%&’+/=?^_`{|}~-]+@[a-zñÑA-Z0-9-]+(?:.[a-zA-Z0-9-]+)$",jugador.getEmail())){
			errors.rejectValue("email", "error",ValidationConstant.EMAIL_FORMATO_ERROR);
		} else {
			Jugador player = jugadorService.findByEmail(jugador.getEmail());
			if(player != null && player.getId() != jugador.getId()) {
				errors.rejectValue("email", "error",ValidationConstant.EMAIL_YAEXISTE_ERROR);
			}
		}
		
		//direccion validation
		if ( StringUtils.isEmpty(jugador.getDireccion()) || jugador.getDireccion().length() < 5) {
			errors.rejectValue("direccion", "error",ValidationConstant.DIRECCION_ERROR);
		}
		
		//localidad validation
		if ( StringUtils.isEmpty(jugador.getLocalidad()) || jugador.getLocalidad().length() < 5) {
			errors.rejectValue("localidad", "error",ValidationConstant.LOCALIDAD_ERROR);
		}
		
		//fecha nacimiento validation
		try {
			if (jugador.getFechaNacimiento() == null) {
				errors.rejectValue("fechaNacimiento", "error",ValidationConstant.VALOR_OBLIGATORIO);
			} else if(jugador.getFechaNacimiento().isAfter(LocalDate.now())) {
				errors.rejectValue("fechaNacimiento", "error",ValidationConstant.FECHA_POSTERIOR_ERROR);
			}
		} catch (Exception e) {
			errors.rejectValue("fechaNacimiento", "error",ValidationConstant.FECHA_FORMATO_ERRONEO_INVERSO);
		}
		
		
		//altura validation
		if (jugador.getAltura() == null) {
			errors.rejectValue("altura", "error",ValidationConstant.VALOR_OBLIGATORIO);
		} else if ( jugador.getAltura() < 100  || jugador.getAltura() > 300) {
			errors.rejectValue("altura", "error",ValidationConstant.ALTURA_ERROR);
		}
		
		
		//peso validation
		if (jugador.getPeso() == null) {
			errors.rejectValue("peso", "error",ValidationConstant.VALOR_OBLIGATORIO);
		} else if ( jugador.getPeso() >= 250 || jugador.getPeso() < 20) {
			errors.rejectValue("peso", "error",ValidationConstant.PESO_ERROR);
		}
		
		
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Jugador.class.isAssignableFrom(clazz);
	}
}


