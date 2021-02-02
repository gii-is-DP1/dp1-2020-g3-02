package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.controller.PasswordController;
import org.springframework.samples.petclinic.web.base.BaseControllerTest;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value = PasswordController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class))
public class PasswordControllerTest extends BaseControllerTest{
	
	@Autowired
	private MockMvc mockMvc;
	
	/*@WithMockUser(value = "spring")
	@Test
	void testProcessEditFormSuccess() throws Exception {
		
		mockMvc.perform(post("/users/postpassword").with(csrf())
				.param("oldPassword", "asdf1234")
				.param("newPassword", "Cayetano15")
				.param("confirmPassword", "Cayetano15")
				.param("user.username", "gonlalle2")
				.param("user.password", "asdf1234")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/home"));
	}*/
}
