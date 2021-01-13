package org.springframework.samples.petclinic.web;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;

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
public class PartidoControllerTest extends BaseControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@WithMockUser(value = "spring")
	@Test
	void testListadoPartidos() throws Exception {

		mockMvc.perform(get("/partidos/showpartidos")).andExpect(status().isOk())
				.andExpect(view().name(ViewConstant.VIEW_PARTIDOS));
	}

	@WithMockUser(value = "spring")
	@Test
	void testVistaEstadisticas() throws Exception {

		when(userService.findByUsername(any(String.class))).thenReturn(getUserJugador());

		mockMvc.perform(get("/partidos/showestadisiticasJugadores")).andExpect(status().isOk())
				.andExpect(view().name(ViewConstant.VIEW_ESTADISTICAS_JUGADOR_POR_PARTIDO))
				.andExpect(model().attributeExists("categorias")).andExpect(model().attributeExists("estadisticas"));
		;
	}

	@WithMockUser(value = "spring")
	@Test
	void testVistaEstadisticasPartido() throws Exception {

		when(userService.findByUsername(any(String.class))).thenReturn(getUserEntrenador());

		mockMvc.perform(get("/partidos/showestadisiticasPartido")).andExpect(status().isOk())
				.andExpect(view().name(ViewConstant.VIEW_ESTADISTICAS_PARTIDO))
				.andExpect(model().attributeExists("categorias"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testGraficoEstadisticasTodosLosPartidosDeUnJugador() throws Exception {

		when(userService.findByUsername(any(String.class))).thenReturn(getUserJugador());

		mockMvc.perform(get("/partidos/findestadisticasJugador/{categoria}", CATEGORIA_EQUIPO))
				.andExpect(jsonPath("$.data[0].id", is(ID)))
				.andExpect(jsonPath("$.data[0].fecha", is(LocalDate.now().toString())))
				.andExpect(jsonPath("$.data[0].hora", is(HORA))).andExpect(status().isOk());
	}

//	@WithMockUser(value = "spring")
//	@Test
//	void testGraficoEstadisticasTodosLosPartidos() throws Exception {
//
//		when(userService.findByUsername(any(String.class))).thenReturn(getUserEntrenador());
//
//		mockMvc.perform(get("/partidos/findestadisticasPartidos/{categoria}", CATEGORIA_EQUIPO)).andExpect(status().isOk());
//	}
//
//	@WithMockUser(value = "spring")
//	@Test
//	void testGraficoEstadisticasTodosLosPartidos() throws Exception {
//
//		mockMvc.perform(get("/partidos/vistaEstadísticasPartidoJugadores")).andExpect(status().isOk());
//	}
//
//	@WithMockUser(value = "spring")
//	@Test
//	void testGraficoEstadisticasTodosLosPartidos() throws Exception {
//
//		mockMvc.perform(get("/partidos/findestadisticasPartidosTodosLosJugadores/{id}", ID)).andExpect(status().isOk());
//	}

	@WithMockUser(value = "spring")
	@Test
	void testFindPartido() throws Exception {

		when(userService.findByUsername(any(String.class))).thenReturn(getUserJugador());

		mockMvc.perform(get("/partidos/findeditpartido/{id}", ID)).andExpect(jsonPath("$.id", is(ID)))
				.andExpect(jsonPath("$.fecha", is(LocalDate.now().toString()))).andExpect(jsonPath("$.hora", is(HORA)))
				.andExpect(status().isOk());
	}

}
