package org.springframework.samples.petclinic.web;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.constant.ViewConstant;
import org.springframework.samples.petclinic.controller.PartidoController;
import org.springframework.samples.petclinic.web.base.BaseControllerTest;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value = PartidoController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class))
class JugadorControllerTest extends BaseControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@WithMockUser(value = "spring")
	@Test
	void testListadoJugadores() throws Exception {

		mockMvc.perform(get("/jugadores/showjugadores")).andExpect(status().isOk())
				.andExpect(view().name(ViewConstant.VIEW_JUGADOR));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testFindEquiposJugador() throws Exception {

		mockMvc.perform(get("/jugadores/getallteamsjugador/{id}", ID))
		.andExpect(jsonPath("$[0]", is("Senior")))
		.andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testListadoJugadoresPrivilegios() throws Exception {

		mockMvc.perform(get("/jugadores/showjugadorespriv")).andExpect(status().isOk())
				.andExpect(view().name(ViewConstant.VIEW_JUGADORES_PRIVILEGIOS));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testListadoJugadoresAutorizacion() throws Exception {

		mockMvc.perform(get("/jugadores/showjugadoresaut")).andExpect(status().isOk())
				.andExpect(view().name(ViewConstant.VIEW_JUGADORES_AUTORIZACION));
	}
	
	//Hay un json dentro de un json?
	@WithMockUser(value = "spring")
	@Test
	void testTablaJugadoresAutorizacion() throws Exception {

		mockMvc.perform(get("/jugadores/tablajugadoresaut"))
				.andExpect(jsonPath("$.data[0].firstName", is("Alberto")))
				.andExpect(jsonPath("$.data[0].lastName", is("Clemente"))).andExpect(status().isOk());
	}
	
	//eliminarAutorizacion
	
	//addAutorizacion
	
	
	@WithMockUser(value = "spring")
	@Test
	void testGraficoEstadisticasJugador() throws Exception {

		when(userService.findByUsername(any(String.class))).thenReturn(getUserJugador());

		mockMvc.perform(get("/jugadores/findestadisticasjugador/{id}", ID))
				.andExpect(jsonPath("$.data[0].id", is(ID)))
				.andExpect(jsonPath("$.data[0].firstName", is("Alberto")))
				.andExpect(jsonPath("$.data[0].lastName", is("Clemente"))).andExpect(status().isOk());
	}
}
