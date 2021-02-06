package org.springframework.samples.petclinic.web;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.constant.ViewConstant;
import org.springframework.samples.petclinic.controller.EquipoController;
import org.springframework.samples.petclinic.web.base.BaseControllerTest;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value = EquipoController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class))
public class EquipoControllerTest extends BaseControllerTest{

	@Autowired
	private MockMvc mockMvc;

	@WithMockUser(value = "spring")
	@Test
	void testListadoEquipos() throws Exception {

		mockMvc.perform(get("/equipos/showequipos")).andExpect(status().isOk())
				.andExpect(view().name(ViewConstant.VIEW_EQUIPOS));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testShowEquipo() throws Exception {

		mockMvc.perform(get("/equipos/showequipo/{id}",ID)).andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testFindEquipos() throws Exception {

		mockMvc.perform(get("/equipos/findEquipos"))	
				.andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testRemoveEquipo() throws Exception {

		mockMvc.perform(get("/equipos/findEquipoEliminar/{id}", ID))
				.andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testEstadisticasEquipo() throws Exception {

		mockMvc.perform(get("/equipos/findEstadisticasEquipo/{id}",ID)).andExpect(status().isOk());
	}
	
	
	@WithMockUser(value = "spring")
	@Test
	void testJugadorPosicionEquipo() throws Exception {

		mockMvc.perform(get("/equipos/findJugadorPosicionEquipo/{id}",ID)).andExpect(status().isOk());
	}
	
	
	@WithMockUser(value = "spring")
	@Test
	void testJugadorNoEquipo() throws Exception {

		mockMvc.perform(get("/equipos/jugadoresNoEquipo/{id}",ID)).andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testFindEquipoCAP() throws Exception {

		mockMvc.perform(get("/equipos/findEquipoCAP/{id}",ID)).andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormSuccess() throws Exception {
		
		mockMvc.perform(post("/equipos/postequipo").with(csrf())
				.param("categoria", "Infantil")
				.param("sistemajuego", "CUATRO_DOS")
				.param("1", "true")
				.param("capitan", "0")
				.param("liga", "Regional")).andExpect(status().is2xxSuccessful());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessRemoveSuccess() throws Exception {
		
		mockMvc.perform(post("/equipos/eliminarequipo/{id}",ID).with(csrf()))
				.andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testFindGetAllTeams() throws Exception {

		mockMvc.perform(get("/equipos/getallteams",ID)).andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testFindEditEquipo() throws Exception {

		mockMvc.perform(get("/equipos/findeditequipo/{id}",ID)).andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testUpdateEquipo() throws Exception {

		mockMvc.perform(post("/equipos/updateequipo").with(csrf())
		.param("id", "1")
		.param("categoria", "Pre-Benjamín")
		.param("sistemajuego", "SEIS_DOS")
		.param("liga", "Andaluza"))
		.andExpect(status().is2xxSuccessful());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testSetCapitanEquipo() throws Exception {
		
		mockMvc.perform(post("/equipos/setCapitanEquipo/{idEquipo}/{idJugador}",ID, ID).with(csrf())).andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testRemovePlayer() throws Exception {
		
		mockMvc.perform(get("/equipos/eliminarjugador/{jugadorID}/{equipoID}",ID, ID))
		.andExpect(view().name("redirect:/equipos/showequipo/"+ID));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testAddPlayer() throws Exception {
		
		mockMvc.perform(get("/equipos/addjugador/{jugadorID}/{equipoID}",ID, ID))
		.andExpect(view().name("redirect:/equipos/showequipo/"+ID));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testEncontrarEquipo() throws Exception {
		
		mockMvc.perform(get("/equipos/findEquipo/{id}",ID))
		.andExpect(jsonPath("$.data[0].id", is(ID)))
		.andExpect(jsonPath("$.data[0].firstName", is("Gonzalo")))
		.andExpect(jsonPath("$.data[0].lastName", is("Lallena")))
		.andExpect(status().isOk());
	}
	
}
