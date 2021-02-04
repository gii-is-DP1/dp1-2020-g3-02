package org.springframework.samples.petclinic.web;

import static org.hamcrest.CoreMatchers.is;
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
import org.springframework.samples.petclinic.controller.EstadisticasController;
import org.springframework.samples.petclinic.web.base.BaseControllerTest;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value = EstadisticasController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class))
public class EstadisticasControllerTest extends BaseControllerTest {
	
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
	void testEstadisticasEntrenamiento() throws Exception {

		mockMvc.perform(get("/estadisticas/estadisticasEntrenamientoForm/{entrenamientoId}", ID))
		.andExpect(model().attributeExists("entrenamiento"))
		.andExpect(view().name(ViewConstant.VIEW_ESTADISTICAS_ENTRENAMIENTO_FORM));
	}
	
//	@WithMockUser(value = "spring")
//	@Test
//	void testTablaIntroducirEstadisticas() throws Exception {
//
//		mockMvc.perform(get("/estadisticas/tablaIntroducirEstadisticas/{partidoId}", ID))
//		.andExpect(jsonPath("$.data[0].id", is(ID)))
//		.andExpect(jsonPath("$.data[0].firstName", is(NOMBRE_JUGADOR)))
//		.andExpect(status().isOk());
//	}
	
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
	void testObtenerEstadisticasJugadoresEntrenamiento() throws Exception {

		mockMvc.perform(get("/estadisticas/obtenerEstadisticasJugadoresEntrenamiento/{entrenamientoId}", ID))
//		.andExpect(jsonPath("$[0].jugadorId", is(ID)))
//		.andExpect(jsonPath("$[0].firstName", is(NOMBRE_COMPLETO)))
//		.andExpect(jsonPath("$[0].fecha", is(LocalDate.now().toString())))
		.andExpect(status().isOk());
	}
	
//	@WithMockUser(value = "spring")
//	@Test
//	void testSaveComandos() throws Exception {
//
//		mockMvc.perform(post("/estadisticas/saveComandos/{partidoId}", ID).with(csrf())
//		.param("comandoIntroducido", "1,s,+;"))
//		.andExpect(status().isOk());
//	}
	
//	@WithMockUser(value = "spring")
//	@Test
//	void testSaveComandosEntrenamiento() throws Exception {
//
//		mockMvc.perform(post("/estadisticas/saveComandosEntrenamiento/{entrenamientoId}", ID).with(csrf())
//		.param("comandoIntroducido", "1,s,+;"))
//		.andExpect(status().isOk());
//	}

//	@WithMockUser(value = "spring")
//	@Test
//	void testRellenarDatos() throws Exception {
//
//		mockMvc.perform(post("/estadisticas/rellenarDatos").with(csrf())
//		.param("partidoId", "20")
//		.param("hour", "18")
//		.param("minute", "30")
//		.param("second", "00"))
//		.andExpect(status().isOk());
//	}
	
//	@WithMockUser(value = "spring")
//	@Test
//	void testRellenarDatosEntrenamiento() throws Exception {
//
//		mockMvc.perform(post("/estadisticas/rellenarDatosEntrenamiento").with(csrf())
//		.param("entrenamientoId", "1")
//		.param("hour", "18"))
//		.andExpect(status().isOk());
//	}
	
//	@WithMockUser(value = "spring")
//	@Test
//	void testCambioSistemaJuegoSustitucion() throws Exception {
//
//		mockMvc.perform(post("/estadisticas/cambioSistemaJuegoSustitucion").with(csrf())
//		.param("partidoId", "1")
//		.param("jugadorEnCampo", "1")
//		.param("jugadorEnBanquillo", "2")
//		.param("minutoAplicacion", "30")
//		.param("sistemajuego", "CINCO_UNO"))
//		.andExpect(status().isOk());
//	}
	
//	@WithMockUser(value = "spring")
//	@Test
//	void testCambioSistemaJuego() throws Exception {
//
//		mockMvc.perform(post("/estadisticas/cambioSistemaJuego").with(csrf())
//		.param("partidoId", "1")
//		.param("minutoAplicacion", "30")
//		.param("sistemajuego", "CINCO_UNO"))
//		.andExpect(status().isOk());
//	}
	
	@WithMockUser(value = "spring")
	@Test
	void testJugadoresEnCampo() throws Exception {

		mockMvc.perform(get("/estadisticas/jugadoresEnCampo/{partidoId}", ID))
		.andExpect(jsonPath("$[0]", is(JUGADOR)))
		.andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testJugadoresEnCampoEntrenamiento() throws Exception {

		mockMvc.perform(get("/estadisticas/jugadoresEnCampoEntrenamiento/{entrenamientoId}", ID))
		.andExpect(jsonPath("$[0]", is(JUGADOR)))
		.andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testJugadoresEnBanquillo() throws Exception {

		mockMvc.perform(get("/estadisticas/jugadoresEnBanquillo/{partidoId}", ID))
//		.andExpect(jsonPath("$[0]", is(JUGADOR)))
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
	
//	@WithMockUser(value = "spring")
//	@Test
//	void testTablaSustituciones() throws Exception {
//
//		mockMvc.perform(get("/estadisticas/tablaSustituciones/{partidoId}", ID))
//		.andExpect(status().isOk());
//	}
	
}
