package org.springframework.samples.petclinic.component;

import java.time.LocalDate;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.constant.ValidationConstant;
import org.springframework.samples.petclinic.model.Entrenador;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.service.EntrenadorService;
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
public class EntrenadorValidator implements Validator{

	@Autowired
	private EntrenadorService entrenadorService;
	
	@Override
	public void validate(Object target, Errors errors) {
		
		Entrenador entrenador = (Entrenador) target;
		
		//Nombre Validation
				if (StringUtils.isEmpty(entrenador.getFirstName())) {
					errors.rejectValue("firstName", "error", ValidationConstant.FIRSTNAME_ERROR);
				}else if(!Pattern.matches("[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]{2,48}",entrenador.getFirstName())){
					errors.rejectValue("firstName", "error",ValidationConstant.FIRSTNAME_ERROR);
				}
				
				//Apellido validation
				if ( StringUtils.isEmpty(entrenador.getLastName()) ) {
					errors.rejectValue("lastName", "error",ValidationConstant.LASTNAME_ERROR);
				}else if(!Pattern.matches("[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]{2,48}",entrenador.getLastName())){
					errors.rejectValue("lastName", "error",ValidationConstant.LASTNAME_ERROR);
				}
				
				//email validation
				if ( StringUtils.isEmpty(entrenador.getEmail())) {
					errors.rejectValue("email", "error",ValidationConstant.VALOR_OBLIGATORIO);
				}else if(!Pattern.matches("^[a-zñÑA-Z0-9.!#$%&’+/=?^_`{|}~-]+@[a-zñÑA-Z0-9-]+(?:.[a-zA-Z0-9-]+)$",entrenador.getEmail())){
					errors.rejectValue("email", "error",ValidationConstant.EMAIL_FORMATO_ERROR);
				} else {
					Entrenador coach = entrenadorService.findByEmail(entrenador.getEmail());
					if(coach != null && coach.getId() != entrenador.getId()) {
						errors.rejectValue("email", "error",ValidationConstant.EMAIL_YAEXISTE_ERROR);
					}
				}
				
				//fecha nacimiento validation
				try {
					if (entrenador.getFechaNacimiento() == null) {
						errors.rejectValue("fechaNacimiento", "error",ValidationConstant.VALOR_OBLIGATORIO);
					} else if(entrenador.getFechaNacimiento().isAfter(LocalDate.now())) {
						errors.rejectValue("fechaNacimiento", "error",ValidationConstant.FECHA_POSTERIOR_ERROR);
					}
				} catch (Exception e) {
					errors.rejectValue("fechaNacimiento", "error",ValidationConstant.FECHA_FORMATO_ERRONEO_INVERSO);
				}
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Entrenador.class.isAssignableFrom(clazz);
	}
}

///** Versión de serialización. */
//private static final long serialVersionUID = 1L;
//
///** Servicio de usuario. */
//@Autowired
//private UsuarioService usuarioService;
//
//@Override
//public boolean supports(final Class<?> clazz) {
//	return UsuarioDTO.class.equals(clazz);
//}
//
//@Override
//public void validate(final Object target, final Errors errors) {
//	final UsuarioDTO usuario = (UsuarioDTO) target;
//	if (usuario.getId() == null) {
//		// validación contraseña obligatoria en alta
//		ValidationUtils.rejectIfEmpty(errors, "usuario.clave", ConstantesController.COD_MSJ_VAL_OBLIG);
//		// validación usuario único
//		final UsuarioDTO usuarioUnico = usuarioService.findByUsuario(usuario.getUsuario());
//		if (usuarioUnico != null) {
//			final Object[] args = new Object[] { "usuario" };
//			errors.rejectValue("usuario.usuario", ConstantesController.COD_MSJ_VAL_YAEXISTE_MASC, args, null);
//		}
//	}
//	// validación contraseña tamaño
//	if (StringUtils.isNotEmpty(usuario.getClave()) && usuario.getClave().length() < 4) {
//		final Object[] args = new Object[] { "usuario", "64", "4" };
//		errors.rejectValue("usuario.clave", ConstantesController.COD_MSJ_VAL_SIZE, args, null);
//	}
//}
