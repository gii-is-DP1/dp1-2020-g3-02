package org.springframework.samples.petclinic.component;

import java.util.Set;

import javax.validation.ConstraintViolation;

import javax.validation.executable.ExecutableValidator;
import javax.validation.metadata.BeanDescriptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.controller.form.JugadorForm;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.service.JugadorService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class JugadorValidator implements Validator {

	@Autowired
	private JugadorService jugadorService;
		
	@Override
	public boolean supports(Class<?> clazz) {
		
		return JugadorForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		final Jugador jugador = (Jugador) target;
		if (jugador.getId() == null) {
		
		ValidationUtils.rejectIfEmpty(errors, "jugador.dni", "El dni es obligatorio");
		
	}
		
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



