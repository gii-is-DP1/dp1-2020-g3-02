package org.springframework.samples.petclinic.component;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.constant.ValidationConstant;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.impl.UserService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class UserValidator implements Validator{
	private static final Log LOG = LogFactory.getLog(PersonalesValidator.class);
	
	@Autowired
	private UserService userService;
	
	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;


		//Password validation
		if (user.getPassword().length()<6) {
			LOG.warn(ValidationConstant.PROPIETARIO_ERROR);
			errors.rejectValue("propietario", "error",ValidationConstant.PROPIETARIO_ERROR);
		}
		
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

}
