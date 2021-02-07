package org.springframework.samples.petclinic.component;

import java.time.LocalDate;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.constant.ValidationConstant;
import org.springframework.samples.petclinic.model.Estadistico;
import org.springframework.samples.petclinic.service.EstadisticoService;
import org.springframework.samples.petclinic.service.impl.UserService;
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
	
	private static final Log LOG = LogFactory.getLog(EstadisticoValidator.class);
	
	@Autowired
	private EstadisticoService estadisticoService;
	
	@Autowired
	private UserService userService;
	
	@Override
	public void validate(Object target, Errors errors) {
		
		Estadistico estadistico = (Estadistico) target;
		
		//Nombre Validation
		if (StringUtils.isEmpty(estadistico.getFirstName())) {
			LOG.warn(ValidationConstant.FIRSTNAME_ERROR);
			errors.rejectValue("firstName", "error", ValidationConstant.FIRSTNAME_ERROR);
		}else if(!Pattern.matches("[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]{2,48}",estadistico.getFirstName())){
			LOG.warn(ValidationConstant.FIRSTNAME_ERROR);
			errors.rejectValue("firstName", "error",ValidationConstant.FIRSTNAME_ERROR);
		}
		
		//Apellido validation
		if ( StringUtils.isEmpty(estadistico.getLastName()) ) {
			LOG.warn(ValidationConstant.LASTNAME_ERROR);
			errors.rejectValue("lastName", "error",ValidationConstant.LASTNAME_ERROR);
		}else if(!Pattern.matches("[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]{2,48}",estadistico.getLastName())){
			LOG.warn(ValidationConstant.LASTNAME_ERROR);
			errors.rejectValue("lastName", "error",ValidationConstant.LASTNAME_ERROR);
		}
		
		//email validation
		if ( StringUtils.isEmpty(estadistico.getEmail())) {
			LOG.warn(ValidationConstant.VALOR_OBLIGATORIO + ": email");
			errors.rejectValue("email", "error",ValidationConstant.VALOR_OBLIGATORIO);
		}else if(!Pattern.matches("^[a-zñÑA-Z0-9.!#$%&’+/=?^_`{|}~-]+@[a-zñÑA-Z0-9-]+(?:.[a-zA-Z0-9-]+)$",estadistico.getEmail())){
			LOG.warn(ValidationConstant.EMAIL_FORMATO_ERROR);
			errors.rejectValue("email", "error",ValidationConstant.EMAIL_FORMATO_ERROR);
		} else {
			Estadistico stat = estadisticoService.findByEmail(estadistico.getEmail());
			if(stat != null && stat.getId() != estadistico.getId()) {
				LOG.warn(ValidationConstant.EMAIL_YAEXISTE_ERROR);
				errors.rejectValue("email", "error",ValidationConstant.EMAIL_YAEXISTE_ERROR);
			}
		}
		
		// user validation
		if (estadistico.getUser() == null) {
			LOG.warn(ValidationConstant.VALOR_OBLIGATORIO + ": user");
			errors.rejectValue("user.username", "error", ValidationConstant.VALOR_OBLIGATORIO);
		} else if (StringUtils.isEmpty(estadistico.getUser().getUsername()) || estadistico.getUser().getUsername().length() <= 6) {
			LOG.warn(ValidationConstant.USERNAME_ERROR);
			errors.rejectValue("user.username", "error", ValidationConstant.USERNAME_ERROR);
		} else if (userService.findByUsername(estadistico.getUser().getUsername()) != null) {
			LOG.warn(ValidationConstant.USERNAME_YAEXISTE_ERROR);
			errors.rejectValue("user.username", "error", ValidationConstant.USERNAME_YAEXISTE_ERROR);
		}
		
		// password validation
		if (estadistico.getUser() != null && (StringUtils.isEmpty(estadistico.getUser().getPassword()) || estadistico.getUser().getPassword().length() < 8)) {
			LOG.warn(ValidationConstant.PASSWORD_ERROR + ": password");
			errors.rejectValue("user.password", "error", ValidationConstant.PASSWORD_ERROR);
		}
		
		//fecha nacimiento validation
		try {
			if (estadistico.getFechaNacimiento() == null) {
				LOG.warn(ValidationConstant.VALOR_OBLIGATORIO + ": fecha");
				errors.rejectValue("fechaNacimiento", "error",ValidationConstant.VALOR_OBLIGATORIO);
			} else if(estadistico.getFechaNacimiento().isAfter(LocalDate.now())) {
				LOG.warn(ValidationConstant.FECHA_POSTERIOR_ERROR);
				errors.rejectValue("fechaNacimiento", "error",ValidationConstant.FECHA_POSTERIOR_ERROR);
			}
		} catch (Exception e) {
			LOG.warn(ValidationConstant.FECHA_FORMATO_ERRONEO);
			errors.rejectValue("fechaNacimiento", "error",ValidationConstant.FECHA_FORMATO_ERRONEO);
		}
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Estadistico.class.isAssignableFrom(clazz);
	}
}
