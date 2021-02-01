package org.springframework.samples.petclinic.model.auxiliares;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEdit {
	private String username;
	private String oldPassword;
	private String newPassword;
	private String confirmPassword;
	
}
