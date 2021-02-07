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
import org.springframework.samples.petclinic.controller.EntrenamientoController;
import org.springframework.samples.petclinic.web.base.BaseControllerTest;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value = EntrenamientoController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class))
public class EntrenamientoControllerTest extends BaseControllerTest{
	
	@Autowired
	private MockMvc mockMvc;

	@WithMockUser(value = "spring")
	@Test
	void testListadoEntrenamientos() throws Exception {
		mockMvc.perform(get("/entrenamientos/showentrenamientos")).andExpect(status().isOk())
				.andExpect(view().name(ViewConstant.VIEW_ENTRENAMIENTOS));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testVistaEstadisticas() throws Exception {

		when(userService.findByUsername(any(String.class))).thenReturn(getUserJugador());

		mockMvc.perform(get("/entrenamientos/showestadisiticasJugadores")).andExpect(status().isOk())
				.andExpect(view().name(ViewConstant.VIEW_ESTADISTICAS_JUGADOR_POR_ENTRENAMIENTO))
				.andExpect(model().attributeExists("categorias")).andExpect(model().attributeExists("estadisticas"));
		;
	}

	@WithMockUser(value = "spring")
	@Test
	void testVistaEstadisticasEntrenamiento() throws Exception {

		when(userService.findByUsername(any(String.class))).thenReturn(getUserEntrenador());

		mockMvc.perform(get("/entrenamientos/showestadisiticasEntrenamiento")).andExpect(status().isOk())
				.andExpect(view().name(ViewConstant.VIEW_ESTADISTICAS_ENTRENAMIENTO))
				.andExpect(model().attributeExists("categorias"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testFindEntrenamiento() throws Exception {

		mockMvc.perform(get("/entrenamientos/findeditentrenamiento/{id}", ID)).andExpect(jsonPath("$.id", is(ID)))
				.andExpect(jsonPath("$.fecha", is(LocalDate.now().toString()))).andExpect(jsonPath("$.hora", is(HORA)))
				.andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring", authorities= {"entrenador"})
	@Test
	void testListadoDeEntrenamientosCoach() throws Exception {
		mockMvc.perform(get("/entrenamientos/findEntrenamientos"))
		.andExpect(jsonPath("$.data[0].fecha", is(LocalDate.now().toString())))
		.andExpect(jsonPath("$.data[0].hora", is(HORA)))
		.andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring", authorities= {"jugador"})
	@Test
	void testListadoDeEntrenamientosPlayer() throws Exception {
		mockMvc.perform(get("/entrenamientos/findEntrenamientos"))
		.andExpect(jsonPath("$.data[0].fecha", is(LocalDate.now().toString())))
		.andExpect(jsonPath("$.data[0].hora", is(HORA)))
		.andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring", authorities= {"estadistico"})
	@Test
	void testListadoDeEntrenamientosStat() throws Exception {
		mockMvc.perform(get("/entrenamientos/findEntrenamientos"))
		.andExpect(jsonPath("$.data[0].fecha", is(LocalDate.now().toString())))
		.andExpect(jsonPath("$.data[0].hora", is(HORA)))
		.andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testShowStatsAllPlayers() throws Exception {
		mockMvc.perform(get("/entrenamientos/showestadisiticasEntrenamientoTodosJugadores")
		.param("id", "1"))
		.andExpect(status().isOk())
		.andExpect(view().name(ViewConstant.VIEW_ESTADISTICAS_ENTRENAMIENTO_JUGADORES));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testFindPosicionEntrenamiento() throws Exception {
		mockMvc.perform(get("/entrenamientos/findJugadorPosicionEntrenamiento/{id}",ID))
		.andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testFindStatsTrainCat() throws Exception {
		mockMvc.perform(get("/entrenamientos/findestadisticasEntrenamientos/{categoria}",CATEGORIA_EQUIPO))
		.andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testFindStatsTrainPlayerCat() throws Exception {
		mockMvc.perform(get("/entrenamientos/findestadisticasJugador/{categoria}",CATEGORIA_EQUIPO))
		.andExpect(status().isOk());
	}
	
	
	@WithMockUser(value = "spring")
	@Test
	void vistaEstadisticasEntrenamientoJugador() throws Exception {

        mockMvc.perform(get("/entrenamientos/showestadisiticasEntrenamientoJugador/{id_jugador}/{id_entrenamiento}",ID,ID))
        .andExpect(model().attributeExists("estadisticas"))
        .andExpect(status().isOk());
    }
	
	@WithMockUser(value = "spring")
	@Test
	void vistaRemoveEntrenamiento() throws Exception {
		
        mockMvc.perform(post("/entrenamientos/removeEntrenamiento/{id}",ID).with(csrf()))
        .andExpect(status().isOk());
    }
	
	@WithMockUser(value = "spring")
	@Test
	void vistaRemoveAsiste() throws Exception {
		
        mockMvc.perform(post("/entrenamientos/eliminarAsisteJugador/{entrenamiento_id}/{jugador_id}",ID, ID).with(csrf()))
        .andExpect(status().isOk());
    }
	@WithMockUser(value = "spring")
	@Test
	void vistaUpdateMaterial() throws Exception {
		mockMvc.perform(post("/entrenamientos/updatematerial", ID).with(csrf())
				.param("id", "1")
				.param("cantidad1", "3")
				.param("cantidad2", "1")
				.param("cantidad3", "2")
				.param("cantidad4", "2")
				.param("cantidad5", "1")
				.param("cantidad6", "1")
				.param("cantidad7", "2")
				.param("cantidad8", "10")
				.param("cantidad9", "2"))
        
        .andExpect(status().isOk());
    }
	@WithMockUser(value = "spring")
	@Test
	void vistaAddNoLlegada() throws Exception {
		
        mockMvc.perform(post("/entrenamientos/confirmacionDeLaNoLlegada/{viaje_id}", ID).with(csrf()))
        .andExpect(status().isOk());
    }
	
	@WithMockUser(value = "spring")
	@Test
	void vistaAddLlegada() throws Exception {
		
        mockMvc.perform(post("/entrenamientos/confirmacionLlegada/{viaje_id}", ID).with(csrf()))
        .andExpect(status().isOk());
    }
	
	@WithMockUser(value = "spring")
	@Test
	void vistaAddAsistencia() throws Exception {
		
        mockMvc.perform(post("/entrenamientos/addAsisteJugador/{entrenamiento_id}/{jugador_id}", ID,ID).with(csrf()))
        .andExpect(status().isOk());
    }
	
	@WithMockUser(value = "spring")
	@Test
	void vistaPostEntrenamiento() throws Exception {
		
        mockMvc.perform(post("/entrenamientos/postentrenamiento").with(csrf())
        		.param("id", "")
        		.param("equipo", "Senior")
        		.param("fecha", "19/02/2021")
        		.param("hora", "16:20"))
        .andExpect(status().is2xxSuccessful());
    }
	
	@WithMockUser(value = "spring")
	@Test
	void vistaPostEntrenamientoBad() throws Exception {
		
        mockMvc.perform(post("/entrenamientos/postentrenamiento").with(csrf())
        		.param("id", "")
        		.param("equipo", "")
        		.param("fecha", "32/02/2021")
        		.param("hora", "15:20"))
        .andExpect(status().isBadRequest());
    }
	
	@WithMockUser(value = "spring")
	@Test
	void vistaEditEntrenamiento() throws Exception {
		
        mockMvc.perform(post("/entrenamientos/postentrenamiento").with(csrf())
        		.param("id", "1")
          		.param("fecha", "19/02/2021")
        		.param("hora", "16:20"))
        .andExpect(status().is2xxSuccessful());
    }
	
	@WithMockUser(value = "spring")
	@Test
	void vistaEditEntrenamientoBad() throws Exception {
		
        mockMvc.perform(post("/entrenamientos/postentrenamiento").with(csrf())
        		.param("id", "1")
          		.param("fecha", "32/12/2021")
        		.param("hora", "16:20"))
        .andExpect(status().isBadRequest());
    }
}
