package org.springframework.samples.petclinic.web;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.constant.ViewConstant;
import org.springframework.samples.petclinic.controller.EjercicioIndividualController;
import org.springframework.samples.petclinic.web.base.BaseControllerTest;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value = EjercicioIndividualController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class))
class EjercicioIndividualControllerTest extends BaseControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@WithMockUser(value = "spring")
	@Test
	void testInicio() throws Exception {

		mockMvc.perform(get("/ejercicios/showejercicios")).andExpect(status().isOk())
				.andExpect(view().name(ViewConstant.VIEW_EJERCICIOS))
				.andExpect(model().attributeExists("permisoEntrenador"))
				.andExpect(model().attributeExists("permisoJugador"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testTablaJugadoresAutorizacion() throws Exception {

		mockMvc.perform(get("/ejercicios/tablaRealizados"))
				.andExpect(jsonPath("$.data[0].posicionJugador", is("COLOCADOR")))
				.andExpect(status().isOk());
	}

}
