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

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.constant.ViewConstant;
import org.springframework.samples.petclinic.controller.EstadisticasEntrenamientoController;
import org.springframework.samples.petclinic.web.base.BaseControllerTest;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value = EstadisticasEntrenamientoController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class))
public class EstadisticasEntrenamientoControllerTest extends BaseControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@WithMockUser(value = "spring")
	@Test
	void testEstadisticasEntrenamiento() throws Exception {

		mockMvc.perform(get("/estadisticasEntrenamiento/estadisticasEntrenamientoForm/{entrenamientoId}", ID))
		.andExpect(model().attributeExists("entrenamiento"))
		.andExpect(view().name(ViewConstant.VIEW_ESTADISTICAS_ENTRENAMIENTO_FORM));
	}
	
	
	@WithMockUser(value = "spring")
	@Test
	void testTablaIntroducirEstadisticasEntrenamiento() throws Exception {

		mockMvc.perform(get("/estadisticasEntrenamiento/tablaIntroducirEstadisticasEntrenamiento/{entrenamientoId}", ID))
		.andExpect(jsonPath("$.data[0].id", is(ID)))
		.andExpect(jsonPath("$.data[0].firstName", is(NOMBRE_JUGADOR)))
		.andExpect(status().isOk());
	}

	@WithMockUser(value = "spring")
	@Test
	void testObtenerEstadisticasJugadoresEntrenamiento() throws Exception {

		mockMvc.perform(get("/estadisticasEntrenamiento/obtenerEstadisticasJugadoresEntrenamiento/{entrenamientoId}", ID))
		.andExpect(jsonPath("$[0].jugadorId", is(ID)))
		.andExpect(jsonPath("$[0].firstName", is(NOMBRE_COMPLETO)))
		.andExpect(jsonPath("$[0].fecha", is(LocalDate.now().toString())))
		.andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testSaveComandosEntrenamiento() throws Exception {

		mockMvc.perform(post("/estadisticasEntrenamiento/saveComandosEntrenamiento/{entrenamientoId}", ID).with(csrf())
		.param("hour", "0")
		.param("minute", "2")
		.param("second", "3")
		.param("comandoIntroducido", "1,s,+"))
		.andExpect(status().isOk());
	}

	@WithMockUser(value = "spring")
	@Test
	void testRellenarDatosEntrenamiento() throws Exception {

		mockMvc.perform(post("/estadisticasEntrenamiento/rellenarDatosEntrenamiento").with(csrf())
		.param("entrenamientoId", "1")
		.param("hour", "0"))
		.andExpect(status().isOk());
	}

	
	@WithMockUser(value = "spring")
	@Test
	void testFinalizarPartido() throws Exception {

		mockMvc.perform(post("/estadisticasEntrenamiento/finalizarEntrenamiento").with(csrf())
		.param("entrenamientoId", "1"))
		.andExpect(status().isOk());
	}
	

	

	

	
}
