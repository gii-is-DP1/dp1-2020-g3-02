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

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.constant.ViewConstant;
import org.springframework.samples.petclinic.controller.PartidoController;
import org.springframework.samples.petclinic.enumerate.TipoViaje;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.Partido;
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

	@WithMockUser(value = "spring")
	@Test
	void testGraficoEstadisticasTodosLosPartidos() throws Exception {

		when(userService.findByUsername(any(String.class))).thenReturn(getUserEntrenador());

		mockMvc.perform(get("/partidos/findestadisticasPartidos/{categoria}", CATEGORIA_EQUIPO))
		.andExpect(jsonPath("$.data[0].id", is(ID)))
		.andExpect(jsonPath("$.data[0].fecha", is(LocalDate.now().toString())))
		.andExpect(jsonPath("$.data[0].hora", is(HORA)))
		.andExpect(status().isOk());
	}

	@WithMockUser(value = "spring")
	@Test
	void vistaEstadisticasTodosLosJugadores() throws Exception {

		mockMvc.perform(get("/partidos/showestadisiticasPartidoTodosJugadores/{id}", ID))
		.andExpect(model().attributeExists("estadisticas"))
		.andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testEstadisticasPartidoJugador() throws Exception {

		mockMvc.perform(get("/partidos/showestadisiticasPartidoJugador/{jugador_id}/{partido_id}", ID, ID))
		.andExpect(model().attributeExists("estadisticas"))
		.andExpect(status().isOk());
	}

	@WithMockUser(value = "spring")
	@Test
	void testGraficoEstadisticasTodosLosJugadores() throws Exception {

		mockMvc.perform(get("/partidos/findestadisticasPartidosTodosLosJugadores/{id}", ID))
		.andExpect(status().isOk());
	}

	@WithMockUser(value = "spring")
	@Test
	void testFindPartido() throws Exception {

		mockMvc.perform(get("/partidos/findeditpartido/{id}", ID)).andExpect(jsonPath("$.id", is(ID)))
				.andExpect(jsonPath("$.fecha", is(LocalDate.now().toString())))
				.andExpect(jsonPath("$.hora", is(HORA)))
				.andExpect(status().isOk());
	}

	@WithMockUser(value = "spring")
	@Test
	void testListadoDePartidos() throws Exception {

		mockMvc.perform(get("/partidos/findPartidos")).andExpect(status().isOk());
	}

	@WithMockUser(value = "spring")
	@Test
	void testFindJugadoresPartidoAutobus() throws Exception {

		Jugador jugador = getJugadorCorrecto();

		when(viajeService.findByJugadorAndPartidoAndTipoViaje(any(Jugador.class), any(Partido.class),
				any(TipoViaje.class))).thenReturn(
						getViajeCorrecto(jugador, jugador.getPartidos().get(0), null, getAutobusCorrecto()));

		mockMvc.perform(get("/partidos/findjugadorespartidoAutobus/{partido_id}", ID))
				.andExpect(jsonPath("$.data[0].viajeId", is(ID))).andExpect(jsonPath("$.data[0].autobusId", is(ID)))
				.andExpect(jsonPath("$.data[0].fecha", is(LocalDate.now().toString())))
				.andExpect(jsonPath("$.data[0].hora", is(HORA))).andExpect(status().isOk());
	}

	@WithMockUser(value = "spring")
	@Test
	void testFindJugadoresPartidoPersonales() throws Exception {

		mockMvc.perform(get("/partidos/findjugadorespartidoPersonales/{partido_id}", ID))
				.andExpect(jsonPath("$.data[0].viajeId", is(ID))).andExpect(jsonPath("$.data[0].propietario", is(NOMBRE_JUGADOR)))
				.andExpect(jsonPath("$.data[0].fecha", is(LocalDate.now().toString())))
				.andExpect(jsonPath("$.data[0].hora", is(HORA))).andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testgraficoJugadorPosicionPartido() throws Exception {

		mockMvc.perform(get("/partidos/findJugadorPosicionPartido/{partido_id}", ID))
				.andExpect(jsonPath("$.data[0].firstName", is("Gonzalo")))
				.andExpect(jsonPath("$.data[0].lastName", is("Lallena")))
				.andExpect(jsonPath("$.data[0].principal", is("COLOCADOR")))
				.andExpect(jsonPath("$.data[0].secundaria", is("OPUESTO")))
				.andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testConfirmarLlegadaAPartidoJugador() throws Exception {

		mockMvc.perform(post("/partidos/confirmacionLlegada/{viaje_id}", ID).with(csrf()))
				.andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testConfirmarNoLlegadaAPartidoJugador() throws Exception {

		mockMvc.perform(post("/partidos/confirmacionDeLaNoLlegada/{viaje_id}", ID).with(csrf()))
				.andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testEliminarJuegaJugador() throws Exception {

		mockMvc.perform(post("/partidos/eliminarjuegaJugador/{partido_id}/{jugador_id}", ID, ID).with(csrf()))
				.andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testAddJuegaJugador() throws Exception {

		mockMvc.perform(post("/partidos/addjuegaJugador/{partido_id}/{jugador_id}", ID, ID).with(csrf()))
				.andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testAddPartido() throws Exception {

		mockMvc.perform(post("/partidos/postpartido").with(csrf())
			.param("id", "1")
			.param("equipo", "Senior")
			.param("fecha", "20/11/2020")
			.param("hora", "11:00"))
			.andExpect(status().is2xxSuccessful());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testEliminarPartido() throws Exception {

		mockMvc.perform(post("/partidos/removePartido/{partido_id}", ID).with(csrf()))
				.andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testListadoDePartidosEquipo() throws Exception {

		mockMvc.perform(get("/partidos/findPartidosEquipo/{equipo_id}", ID))
				.andExpect(jsonPath("$.data[0].fecha", is(LocalDate.now().toString())))
				.andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testFindVehiculos() throws Exception {

		mockMvc.perform(get("/partidos/findVehiculos/{id}/{tipo_viaje}", ID, TipoViaje.IDA))
				.andExpect(jsonPath("$.data[0].id", is(ID)))
				.andExpect(jsonPath("$.data[0].propietario", is("Gonzalo")))
				.andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void addViajeIDA() throws Exception {
		
		when(userService.findByUsername(any(String.class))).thenReturn(getUserJugador());

		mockMvc.perform(post("/partidos/postviaje").with(csrf())
				.param("idPartido", "1")
				.param("tipoViaje", "IDA")
				.param("propietario", "1"))
				.andExpect(status().is2xxSuccessful());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void addViajeIDAYVUELTA() throws Exception {
		
		when(userService.findByUsername(any(String.class))).thenReturn(getUserJugador());

		mockMvc.perform(post("/partidos/postviaje").with(csrf())
				.param("idPartido", "1")
				.param("tipoViaje", "IDAYVUELTA")
				.param("propietario", "1"))
				.andExpect(status().is2xxSuccessful());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void addViajeBusIDA() throws Exception {
		
		when(userService.findByUsername(any(String.class))).thenReturn(getUserJugador());

		mockMvc.perform(post("/partidos/postbus").with(csrf())
				.param("idPartidoBus", "1")
				.param("tipoViaje", "IDA"))
				.andExpect(status().is2xxSuccessful());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void addViajeBusIDAYVUELTA() throws Exception {
		
		when(userService.findByUsername(any(String.class))).thenReturn(getUserJugador());

		mockMvc.perform(post("/partidos/postbus").with(csrf())
				.param("idPartidoBus", "1")
				.param("tipoViaje", "IDAYVUELTA"))
				.andExpect(status().is2xxSuccessful());
	}
	
}
