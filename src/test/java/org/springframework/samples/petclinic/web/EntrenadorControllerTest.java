package org.springframework.samples.petclinic.web;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.component.EntrenadorValidator;
import org.springframework.samples.petclinic.constant.ViewConstant;
import org.springframework.samples.petclinic.controller.EntrenadorController;
import org.springframework.samples.petclinic.service.EntrenadorService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value = EntrenadorController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class))
public class EntrenadorControllerTest {

	@MockBean
	private EntrenadorService entrenadorService;

	@MockBean
	private EntrenadorValidator entrenadorValidator;

	@Autowired
	private MockMvc mockMvc;

	@WithMockUser(value = "spring")
	@Test
	void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/entrenadores/entrenadorform?id=0")).andExpect(status().isOk())
				.andExpect(view().name(ViewConstant.VIEWS_ENTRENADOR_CREATE_OR_UPDATE_FORM))
				.andExpect(model().attributeExists("entrenador"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/entrenadores/addentrenador").param("firstName", "Pepe")
				.param("lastName", "Cayetano")
				.with(csrf())
				.param("email", "cucutras@gmail.com")
				.param("fechaNacimiento", "1990/10/10")
				.param("user.username", "testEntrenadorUser")
				.param("user.password", "testEntrenadorUser")).andExpect(status().is3xxRedirection());
				//.andExpect(view().name("redirect:/home"));
	}

}
