package org.springframework.samples.petclinic.component;

import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.constant.ValidationConstant;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.model.auxiliares.UserEdit;
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
public class UserValidator implements Validator{
	private static final Log LOG = LogFactory.getLog(PersonalesValidator.class);
	
	@Autowired
	private UserService userService;
	
	@Override
	public void validate(Object target, Errors errors) {
		UserEdit userEdit = (UserEdit) target;
		
		final User user= userService.findByUsername(userEdit.getUsername());

		//Password validation
		if(StringUtils.isEmpty(userEdit.getOldPassword())) {
			LOG.warn(ValidationConstant.VALOR_OBLIGATORIO);
			errors.rejectValue("oldPassword", "error",ValidationConstant.VALOR_OBLIGATORIO);
		}else {
		if(!user.getPassword().equals(userEdit.getOldPassword())){
			LOG.warn(ValidationConstant.PASSWORD_ERROR3);
			errors.rejectValue("oldPassword", "error",ValidationConstant.PASSWORD_ERROR3);
		}else {
			if (!Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{6,12}$", userEdit.getNewPassword())) {
				LOG.warn(ValidationConstant.PASSWORD_ERROR);
				errors.rejectValue("newPassword", "error",ValidationConstant.PASSWORD_ERROR);
			}
			
			if (user.getPassword().equals(userEdit.getNewPassword())) {
				LOG.warn(ValidationConstant.PASSWORD_ERROR2);
				errors.rejectValue("confirmPassword", "error",ValidationConstant.PASSWORD_ERROR2);
			}
			
			if (!userEdit.getConfirmPassword().equals(userEdit.getNewPassword())) {
				LOG.warn(ValidationConstant.PASSWORD_ERROR4);
				errors.rejectValue("confirmPassword", "error",ValidationConstant.PASSWORD_ERROR4);
			}
		}}
		
		
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return UserEdit.class.isAssignableFrom(clazz);
	}

}
