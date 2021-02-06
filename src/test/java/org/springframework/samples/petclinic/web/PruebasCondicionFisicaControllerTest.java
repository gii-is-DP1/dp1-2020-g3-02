package org.springframework.samples.petclinic.web;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.controller.PruebaCondicionFisicaController;
import org.springframework.samples.petclinic.enumerate.TipoPrueba;
import org.springframework.samples.petclinic.web.base.BaseControllerTest;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value = PruebaCondicionFisicaController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class))
public class PruebasCondicionFisicaControllerTest extends BaseControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormSuccess() throws Exception {
		
		mockMvc.perform(post("/pruebas/addprueba").with(csrf())
				.param("id", "1")
				.param("tipoPrueba", "ABDOMINAL")
				.param("dato", "11"))
				.andExpect(status().isOk());				
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormError() throws Exception {
		
		mockMvc.perform(post("/pruebas/addprueba").with(csrf())
				.param("id", "1")
				.param("tipoPrueba", "NOSOYUNTIPODEPRUEBA")
				.param("dato", "11"))
				.andExpect(status().isBadRequest());				
	}

	@WithMockUser(value = "spring")
	@Test
	void testFindPruebasCondicionFisicaJugador() throws Exception {

		mockMvc.perform(get("/pruebas/findPruebasCondicionFisicaJugador/{id}/{tipo}", ID,TipoPrueba.ABDOMINAL))
				.andExpect(jsonPath("$.data[0].id", is(ID)))
				.andExpect(jsonPath("$.data[0].fecha", is(LocalDate.now().toString())))
				.andExpect(jsonPath("$.data[0].dato", is(10.)))
				.andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testFindPruebasCondicionFisicaJugadorByID() throws Exception {

		mockMvc.perform(get("/pruebas/findPruebasCondicionFisicaById/{id}", ID))
				.andExpect(jsonPath("$.id", is(ID)))
				.andExpect(jsonPath("$.tipoPrueba", is("ABDOMINAL")))
				.andExpect(jsonPath("$.fecha", is(LocalDate.now().toString())))
				.andExpect(jsonPath("$.dato", is(10.)))
				.andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessDeleteFormSuccess() throws Exception {
		
		mockMvc.perform(post("/pruebas/eliminarprueba").with(csrf())
				.param("id", "1"))
				.andExpect(status().isOk());				
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessDeleteFormError() throws Exception {
		
		mockMvc.perform(post("/pruebas/eliminarprueba").with(csrf())
				.param("id", "10000000000000000"))
				.andExpect(status().isBadRequest());				
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessUpdateFormSuccess() throws Exception {
		
		mockMvc.perform(post("/pruebas/updateprueba").with(csrf())
				.param("id", "1")
				.param("fecha", "11/12/2020")
				.param("dato", "11"))
				.andExpect(status().isOk());				
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessUpdateFormError() throws Exception {
		
		mockMvc.perform(post("/pruebas/updateprueba").with(csrf())
				.param("id", "ESTONOESUNID")
				.param("fecha", "11/12/2020")
				.param("dato", "11"))
				.andExpect(status().isBadRequest());				
	}

}
