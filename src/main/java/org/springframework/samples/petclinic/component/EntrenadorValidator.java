package org.springframework.samples.petclinic.component;

import java.time.LocalDate;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.constant.ValidationConstant;
import org.springframework.samples.petclinic.model.Entrenador;
import org.springframework.samples.petclinic.service.EntrenadorService;
import org.springframework.samples.petclinic.service.impl.UserService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class EntrenadorValidator implements Validator {

	private static final Log LOG = LogFactory.getLog(EntrenadorValidator.class);

	@Autowired
	private EntrenadorService entrenadorService;
	
	@Autowired
	private UserService userService;

	@Override
	public void validate(Object target, Errors errors) {

		Entrenador entrenador = (Entrenador) target;

		// Nombre Validation
		if (StringUtils.isEmpty(entrenador.getFirstName())) {
			LOG.warn(ValidationConstant.FIRSTNAME_ERROR);
			errors.rejectValue("firstName", "error", ValidationConstant.FIRSTNAME_ERROR);
		} else if (!Pattern.matches(
				"[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]{2,48}",
				entrenador.getFirstName())) {
			LOG.warn(ValidationConstant.FIRSTNAME_ERROR);
			errors.rejectValue("firstName", "error", ValidationConstant.FIRSTNAME_ERROR);
		}

		// Apellido validation
		if (StringUtils.isEmpty(entrenador.getLastName())) {
			LOG.warn(ValidationConstant.LASTNAME_ERROR);
			errors.rejectValue("lastName", "error", ValidationConstant.LASTNAME_ERROR);
		} else if (!Pattern.matches(
				"[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]{2,48}",
				entrenador.getLastName())) {
			LOG.warn(ValidationConstant.LASTNAME_ERROR);
			errors.rejectValue("lastName", "error", ValidationConstant.LASTNAME_ERROR);
		}

		// email validation
		if (StringUtils.isEmpty(entrenador.getEmail())) {
			LOG.warn(ValidationConstant.VALOR_OBLIGATORIO + ": email");
			errors.rejectValue("email", "error", ValidationConstant.VALOR_OBLIGATORIO);
		} else if (!Pattern.matches("^[a-zñÑA-Z0-9.!#$%&’+/=?^_`{|}~-]+@[a-zñÑA-Z0-9-]+(?:.[a-zA-Z0-9-]+)$",
				entrenador.getEmail())) {
			LOG.warn(ValidationConstant.EMAIL_FORMATO_ERROR);
			errors.rejectValue("email", "error", ValidationConstant.EMAIL_FORMATO_ERROR);
		} else {
			Entrenador coach = entrenadorService.findByEmail(entrenador.getEmail());
			if (coach != null && coach.getId() != entrenador.getId()) {
				LOG.warn(ValidationConstant.EMAIL_YAEXISTE_ERROR);
				errors.rejectValue("email", "error", ValidationConstant.EMAIL_YAEXISTE_ERROR);
			}
		}
		
		// user validation
		if (entrenador.getUser() == null) {
			LOG.warn(ValidationConstant.VALOR_OBLIGATORIO + ": user");
			errors.rejectValue("user.username", "error", ValidationConstant.VALOR_OBLIGATORIO);
		} else if (StringUtils.isEmpty(entrenador.getUser().getUsername()) || entrenador.getUser().getUsername().length() <= 6) {
			LOG.warn(ValidationConstant.USERNAME_ERROR);
			errors.rejectValue("user.username", "error", ValidationConstant.USERNAME_ERROR);
		} else if (userService.findByUsername(entrenador.getUser().getUsername()) != null) {
			LOG.warn(ValidationConstant.USERNAME_YAEXISTE_ERROR);
			errors.rejectValue("user.username", "error", ValidationConstant.USERNAME_YAEXISTE_ERROR);
		}
		
		// password validation
		if (entrenador.getUser() != null && (StringUtils.isEmpty(entrenador.getUser().getPassword()) || entrenador.getUser().getPassword().length() < 8)) {
			LOG.warn(ValidationConstant.PASSWORD_ERROR + ": password");
			errors.rejectValue("user.password", "error", ValidationConstant.PASSWORD_ERROR);
		}		

		// fecha nacimiento validation
		try {
			if (entrenador.getFechaNacimiento() == null) {
				LOG.warn(ValidationConstant.VALOR_OBLIGATORIO + ": fechaNacimiento");
				errors.rejectValue("fechaNacimiento", "error", ValidationConstant.VALOR_OBLIGATORIO);
			} else if (entrenador.getFechaNacimiento().isAfter(LocalDate.now())) {
				LOG.warn(ValidationConstant.FECHA_POSTERIOR_ERROR);
				errors.rejectValue("fechaNacimiento", "error", ValidationConstant.FECHA_POSTERIOR_ERROR);
			}
		} catch (Exception e) {
			LOG.warn(ValidationConstant.FECHA_FORMATO_ERRONEO);
			errors.rejectValue("fechaNacimiento", "error", ValidationConstant.FECHA_FORMATO_ERRONEO);
		}
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Entrenador.class.isAssignableFrom(clazz);
	}
}
