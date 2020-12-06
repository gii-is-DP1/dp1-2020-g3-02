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


@Component
public class JugadorValidator implements Validator {

	@Autowired
	private JugadorService jugadorService;
  
	@Override
	public void validate(Object target, Errors errors) {
		Jugador jugador = (Jugador) target;
		
		//Dni validation
		if ( jugador.getDni() == null || jugador.getDni().length() != 9) {
			errors.rejectValue("dni", "El DNI debe tener 8 números y una letra","El DNI debe tener 8 números y una letra");
		}
		
		//Nombre Validation
		if ( jugador.getFirstName() == null) {
			errors.rejectValue("firstName", "error", ValidationConstant.FIRSTNAME_ERROR);
		}else if(!Pattern.matches("[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]{2,48}",jugador.getFirstName())){
			errors.rejectValue("firstName", "error",ValidationConstant.FIRSTNAME_ERROR);
		}
		
		//Apellido validation
		if ( jugador.getLastName() == null) {
			errors.rejectValue("lastName", "error",ValidationConstant.LASTNAME_ERROR);
		}else if(!Pattern.matches("[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]{2,48}",jugador.getLastName())){
			errors.rejectValue("firstName", "error",ValidationConstant.LASTNAME_ERROR);
		}
		
		//email validation
		if ( jugador.getEmail() == null) {
			errors.rejectValue("email", "error",ValidationConstant.VALOR_OBLIGATORIO);
		}else if(!Pattern.matches("^[a-zA-Z0-9.!#$%&’+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:.[a-zA-Z0-9-]+)$",jugador.getEmail())){
			errors.rejectValue("firstName", "error",ValidationConstant.EMAIL_FORMATO_ERROR);
		}
		
		//direccion validation
		if ( jugador.getDireccion() == null || jugador.getDireccion().length() < 5) {
			errors.rejectValue("direccion", "La dirección es requerida y debe tener al menos 5 caracteres","La dirreción es requerida y debe tener al menos 5 caracteres");
		}
		
		//localidad validation
		if ( jugador.getLocalidad() == null || jugador.getLocalidad().length() < 5) {
			errors.rejectValue("localidad", "La localidad es requerida y debe tener al menos 5 caracteres","La localidad es requerida y debe tener al menos 5 caracteres");
		}
		
		//fecha nacimiento validation
		try {
			if (jugador.getFechaNacimiento() == null || jugador.getFechaNacimiento().isAfter(LocalDate.now())) {
				errors.rejectValue("fechaNacimiento", "La fecha de nacimiento es requerida y debe ser anterior al día de hoy","La fecha de nacimiento es requerida y debe ser anterior al día de hoy");
			}
		} catch (Exception e) {
			errors.rejectValue("fechaNacimiento", "La fecha debe tener el formato requerido(YYYY/MM/DD)","La fecha debe tener el formato requerido(YYYY/MM/DD)");
		}
		
		
		//altura validation
		if (jugador.getAltura() == null) {
			errors.rejectValue("altura", "error",ValidationConstant.VALOR_OBLIGATORIO);
		} else if ( jugador.getAltura() < 100  || jugador.getAltura() > 300) {
			errors.rejectValue("altura", "La altura debe estar entre 1 y 3 metros","La altura debe estar entre 1 y 3 metros");
		}
		
		
		//peso validation
		if (jugador.getPeso() == null) {
			errors.rejectValue("peso", "error",ValidationConstant.VALOR_OBLIGATORIO);
		} else if ( jugador.getPeso() >= 250 || jugador.getPeso() < 20) {
			errors.rejectValue("peso", "El peso debe estar entre 20 y 250 Kg","El peso debe estar entre 20 y 250 Kg");
		}
		
		
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Jugador.class.isAssignableFrom(clazz);
	}
}

//	/** Versión de serialización. */
//	private static final long serialVersionUID = 1L;
//
//	/** Servicio de usuario. */
//	@Autowired
//	private UsuarioService usuarioService;
//
//	@Override
//	public boolean supports(final Class<?> clazz) {
//		return UsuarioDTO.class.equals(clazz);
//	}
//
//	@Override
//	public void validate(final Object target, final Errors errors) {
//		final UsuarioDTO usuario = (UsuarioDTO) target;
//		if (usuario.getId() == null) {
//			// validación contraseña obligatoria en alta
//			ValidationUtils.rejectIfEmpty(errors, "usuario.clave", ConstantesController.COD_MSJ_VAL_OBLIG);
//			// validación usuario único
//			final UsuarioDTO usuarioUnico = usuarioService.findByUsuario(usuario.getUsuario());
//			if (usuarioUnico != null) {
//				final Object[] args = new Object[] { "usuario" };
//				errors.rejectValue("usuario.usuario", ConstantesController.COD_MSJ_VAL_YAEXISTE_MASC, args, null);
//			}
//		}
//		// validación contraseña tamaño
//		if (StringUtils.isNotEmpty(usuario.getClave()) && usuario.getClave().length() < 4) {
//			final Object[] args = new Object[] { "usuario", "64", "4" };
//			errors.rejectValue("usuario.clave", ConstantesController.COD_MSJ_VAL_SIZE, args, null);
//		}
//	}



