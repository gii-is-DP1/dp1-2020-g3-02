package org.springframework.samples.petclinic.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.model.auxiliares.UserEdit;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
	
	@Autowired
	UserConverter userConverter;
	
	public UserEdit convertUserToUserEdit(User user) {
		
		UserEdit userEdit = new UserEdit();
		
		userEdit.setUsername(user.getUsername());
		userEdit.setOldPassword(user.getPassword());
		return userEdit;
	}
	
	public User convertUserEditToUser(UserEdit userEdit) {
		
		User user = new User();
		
		user.setPassword(userEdit.getConfirmPassword());
		return user;
	}
}
