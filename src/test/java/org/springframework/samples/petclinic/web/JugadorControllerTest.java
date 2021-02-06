package org.springframework.samples.petclinic.web;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.springframework.samples.petclinic.controller.JugadorController;
import org.springframework.samples.petclinic.web.base.BaseControllerTest;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value = JugadorController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class))
class JugadorControllerTest extends BaseControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@WithMockUser(value = "spring")
	@Test
	void testListadoJugadores() throws Exception {

		mockMvc.perform(get("/jugadores/showjugadores")).andExpect(status().isOk())
				.andExpect(view().name(ViewConstant.VIEW_JUGADOR))
				.andExpect(model().attributeExists("username"))
				.andExpect(model().attributeExists("estadoLesionado"))
				.andExpect(model().attributeExists("jugadores"));
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

		mockMvc.perform(get("/jugadores/showjugadorespriv"))
				.andExpect(view().name(ViewConstant.VIEW_JUGADORES_PRIVILEGIOS))
				.andExpect(model().attributeExists("entrenamientos"))
				.andExpect(model().attributeExists("partidos"))
				.andExpect(model().attributeExists("jugadorespriv"))
				.andExpect(model().attributeExists("listpriv"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testListadoJugadoresAutorizacion() throws Exception {

		mockMvc.perform(get("/jugadores/showjugadoresaut")).andExpect(status().isOk())
				.andExpect(view().name(ViewConstant.VIEW_JUGADORES_AUTORIZACION))
				.andExpect(model().attributeExists("transporte"))
				.andExpect(model().attributeExists("usoimagen"))
				.andExpect(model().attributeExists("lesion"))
				.andExpect(model().attributeExists("excursiones"))
				.andExpect(model().attributeExists("jugadoresaut"))
				.andExpect(model().attributeExists("listaut"));
	}
	
	//Hay un json dentro de un json?
	@WithMockUser(value = "spring")
	@Test
	void testTablaJugadoresAutorizacion() throws Exception {

		mockMvc.perform(get("/jugadores/tablajugadoresaut"))
				.andExpect(jsonPath("$.data[0].firstName", is("Gonzalo")))
				.andExpect(jsonPath("$.data[0].lastName", is("Lallena"))).andExpect(status().isOk());
	}
	
	//eliminarAutorizacion
	
	//addAutorizacion
	
	
	@WithMockUser(value = "spring")
	@Test
	void testGraficoEstadisticasJugador() throws Exception {

		when(userService.findByUsername(any(String.class))).thenReturn(getUserEntrenador());

		mockMvc.perform(get("/jugadores/findestadisticasjugador/{id}", ID))
				.andExpect(jsonPath("$.id", is(ID)))
				.andExpect(jsonPath("$.firstName", is("Gonzalo")))
				.andExpect(jsonPath("$.lastName", is("Lallena"))).andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testEditarJugador() throws Exception {

		when(userService.findByUsername(any(String.class))).thenReturn(getUserEntrenador());

		mockMvc.perform(get("/jugadores/findeditjugador/{id}", ID))
				.andExpect(jsonPath("$.id", is(ID)))
				.andExpect(jsonPath("$.firstName", is("Gonzalo")))
				.andExpect(jsonPath("$.lastName", is("Lallena")))
				.andExpect(jsonPath("$.dni", is("11111111A")))
				.andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testEditarJugadorNumCamiseta() throws Exception {

		when(userService.findByUsername(any(String.class))).thenReturn(getUserEntrenador());

		mockMvc.perform(get("/jugadores/findEditjugadorNumCamiseta/{jugadorID}/{equipoID}", ID, ID))
				.andExpect(jsonPath("$.id", is(ID)))
				.andExpect(jsonPath("$.firstName", is("Gonzalo")))
				.andExpect(jsonPath("$.lastName", is("Lallena")))
				.andExpect(jsonPath("$.numCamiseta", is(1)))
				.andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testRedirectJugadorForm() throws Exception {

		mockMvc.perform(get("/jugadores/jugadorform?id=0")).andExpect(status().isOk())
				.andExpect(view().name(ViewConstant.VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM))
				.andExpect(model().attributeExists("jugador"));
	}
	
	//He añadido metodo save del service al base mock controller ¿Está bien?
	@WithMockUser(value = "spring")
	@Test
	void testAddJugador() throws Exception {
		
		mockMvc.perform(post("/jugadores/addjugador").with(csrf())
		.param("firstName", "Diego")
		.param("lastName", "Hill")
		.param("dni", "13579246A")
		.param("direccion", "C/GitGud")
		.param("localidad", "AnorLondo")
		.param("fechaNacimiento", "01/10/2000")
		.param("altura", "182")
		.param("peso", "70")
		.param("estadoActual", "EN_FORMA")
		.param("username", "lololololo")
		.param("email", "abobole@gmail.com")
		.param("posicionPrincipal", "PUNTA")
		.param("posicionSecundaria", "OPUESTO"))
		.andExpect(view().name("redirect:/home"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testUpdateJugador() throws Exception {
		
		mockMvc.perform(post("/jugadores/updatejugador").with(csrf())
		.param("id", "1")
		.param("firstName", "Diego")
		.param("lastName", "Hill")
		.param("dni", "13579246A")
		.param("direccion", "C/GitGud")
		.param("localidad", "AnorLondo")
		.param("fechaNacimiento", "01/10/2000")
		.param("altura", "182")
		.param("peso", "70")
		.param("estadoActual", "EN_FORMA")
		.param("email", "abobole@gmail.com")
		.param("posicionPrincipal", "PUNTA")
		.param("posicionSecundaria", "OPUESTO"))
		.andExpect(status().is2xxSuccessful());
	}
	
}
