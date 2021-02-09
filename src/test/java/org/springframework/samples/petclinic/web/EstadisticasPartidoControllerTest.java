package org.springframework.samples.petclinic.web;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.constant.ViewConstant;
import org.springframework.samples.petclinic.controller.EstadisticasPartidoController;
import org.springframework.samples.petclinic.web.base.BaseControllerTest;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value = EstadisticasPartidoController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class))
public class EstadisticasPartidoControllerTest extends BaseControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@WithMockUser(value = "spring")
	@Test
	void testEstadisticasPartidos() throws Exception {

		mockMvc.perform(get("/estadisticas/estadisticasPartidoForm/{partidoId}", ID))
		.andExpect(model().attributeExists("partido"))
		.andExpect(view().name(ViewConstant.VIEW_ESTADISTICAS_PARTIDO_FORM));
	}
	
	
	@WithMockUser(value = "spring")
	@Test
	void testTablaIntroducirEstadisticas() throws Exception {

		mockMvc.perform(get("/estadisticas/tablaIntroducirEstadisticas/{partidoId}", ID))
		.andExpect(jsonPath("$.data[0].id", is(ID)))
		.andExpect(jsonPath("$.data[0].firstName", is(NOMBRE_JUGADOR)))
		.andExpect(status().isOk());
	}
	

	@WithMockUser(value = "spring")
	@Test
	void testObtenerEstadisticasJugadores() throws Exception {

		mockMvc.perform(get("/estadisticas/obtenerEstadisticasJugadores/{partidoId}", ID))
		.andExpect(jsonPath("$[0].jugadorId", is(ID)))
		.andExpect(jsonPath("$[0].firstName", is(NOMBRE_COMPLETO)))
		.andExpect(jsonPath("$[0].fecha", is(LocalDate.now().toString())))
		.andExpect(status().isOk());
	}

	@WithMockUser(value = "spring")
	@Test
	void testSaveComandos() throws Exception {

		mockMvc.perform(post("/estadisticas/saveComandos/{partidoId}", ID).with(csrf())
		.param("hour", "0")
		.param("minute", "2")
		.param("second", "3")
		.param("comandoIntroducido", "1,s,+"))
		.andExpect(status().isOk());
	}

	@WithMockUser(value = "spring")
	@Test
	void testRellenarDatos() throws Exception {

		mockMvc.perform(post("/estadisticas/rellenarDatos").with(csrf())
		.param("partidoId", "1")
		.param("hour", "0")
		.param("minute", "3")
		.param("second", "4"))
		.andExpect(status().isOk());
	}

	@WithMockUser(value = "spring")
	@Test
	void testCambioSistemaJuegoSustitucion() throws Exception {

		mockMvc.perform(post("/estadisticas/cambioSistemaJuegoSustitucion").with(csrf())
		.param("partidoId", "1")
		.param("jugadorEnCampo", "1")
		.param("jugadorEnBanquillo", "2")
		.param("minutoAplicacion", "30")
		.param("sistemajuego", "CINCO_UNO"))
		.andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testCambioSistemaJuego() throws Exception {

		mockMvc.perform(post("/estadisticas/cambioSistemaJuego").with(csrf())
		.param("partidoId", "1")
		.param("minutoAplicacion", "30")
		.param("sistemajuego", "CINCO_UNO"))
		.andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testJugadoresEnCampo() throws Exception {

		mockMvc.perform(get("/estadisticas/jugadoresEnCampo/{partidoId}", ID))
		.andExpect(jsonPath("$[0]", is(JUGADOR)))
		.andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testJugadoresEnBanquillo() throws Exception {

		mockMvc.perform(get("/estadisticas/jugadoresEnBanquillo/{partidoId}", ID))
		.andExpect(jsonPath("$", is(Lists.emptyList())))
		.andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testRealizarSustitucion() throws Exception {

		mockMvc.perform(post("/estadisticas/realizarsustitucion").with(csrf())
		.param("partidoId", "1")
		.param("jugadorEnCampo", "1")
		.param("jugadorEnBanquillo", "2")
		.param("minutoSustitucion", "30"))
		.andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testAnadirJugadorJugando() throws Exception {

		mockMvc.perform(post("/estadisticas/anadirJugadorJugando").with(csrf())
		.param("partidoId", "1")
		.param("jugadorId", "1"))
		.andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testEliminarJugadorJugando() throws Exception {

		mockMvc.perform(post("/estadisticas/eliminarJugadorJugando").with(csrf())
		.param("partidoId", "1")
		.param("jugadorId", "1"))
		.andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testSeleccionarLibero() throws Exception {

		mockMvc.perform(post("/estadisticas/seleccionarLibero").with(csrf())
		.param("partidoId", "1")
		.param("jugadorId", "1"))
		.andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testEliminarLibero() throws Exception {

		mockMvc.perform(post("/estadisticas/eliminarLibero").with(csrf())
		.param("partidoId", "1")
		.param("jugadorId", "1"))
		.andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testMasNosotros() throws Exception {

		mockMvc.perform(post("/estadisticas/masNosotros").with(csrf())
		.param("partidoId", "1")
		.param("set", "1"))
		.andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testMenosNosotros() throws Exception {

		mockMvc.perform(post("/estadisticas/menosNosotros").with(csrf())
		.param("partidoId", "1")
		.param("set", "1"))
		.andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testMasEllos() throws Exception {

		mockMvc.perform(post("/estadisticas/masEllos").with(csrf())
		.param("partidoId", "1")
		.param("set", "1"))
		.andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testMenosEllos() throws Exception {

		mockMvc.perform(post("/estadisticas/menosEllos").with(csrf())
		.param("partidoId", "1")
		.param("set", "1"))
		.andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testFinalizarPartido() throws Exception {

		mockMvc.perform(post("/estadisticas/finalizarPartido").with(csrf())
		.param("partidoId", "1"))
		.andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testGetMarcador() throws Exception {

		mockMvc.perform(get("/estadisticas/getMarcador/{partidoId}", ID))
		.andExpect(jsonPath("$[0]", is(MARCADOR)))
		.andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testGetSistemaJuegoEquipo() throws Exception {

		mockMvc.perform(get("/estadisticas/sistemaJuegoEquipo/{partidoId}", ID))
		.andExpect(jsonPath("$", is(SISTEMA_JUEGO)))
		.andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testIniciarPartido() throws Exception {

		mockMvc.perform(post("/estadisticas/iniciarPartido").with(csrf())
		.param("partidoId", "1")
		.param("sistemaJuego", SISTEMA_JUEGO))
		.andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testTablaSustituciones() throws Exception {

		mockMvc.perform(get("/estadisticas/tablaSustituciones/{partidoId}", ID))
		.andExpect(jsonPath("$.data[0].id", is(ID)))
		.andExpect(jsonPath("$.data[0].firstName", is(NOMBRE_JUGADOR)))
		.andExpect(status().isOk());
	}
	
}
