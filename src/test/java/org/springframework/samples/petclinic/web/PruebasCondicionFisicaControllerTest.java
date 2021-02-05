package org.springframework.samples.petclinic.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.enumerate.TipoPrueba;
import org.springframework.samples.petclinic.web.base.BaseControllerTest;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value = PruebasCondicionFisicaControllerTest.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class))
public class PruebasCondicionFisicaControllerTest extends BaseControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	
//	@WithMockUser(value = "spring")
//	@Test
//	void testProcessCreationFormSuccess() throws Exception {
//		
//		mockMvc.perform(post("/pruebas/addprueba").with(csrf())
//				.param("id", "1")
//				.param("tipoPrueba", "ABDOMINAL")
//				.param("dato", "11")).andExpect(status().is3xxRedirection())
//				.andExpect(view().name("redirect:/home"));
//	}

	@WithMockUser(value = "spring")
	@Test
	void testFindPruebasCondicionFisicaJugador() throws Exception {

		mockMvc.perform(get("/pruebas/findPruebasCondicionFisicaJugador/{id}/{tipo}", ID,TipoPrueba.ABDOMINAL))
//				.andExpect(jsonPath("$.data.id[0]", is(ID)))
//				.andExpect(jsonPath("$.data[0].fecha", is(LocalDate.now().toString())))
//				.andExpect(jsonPath("$.data[0].dato", is(10)))
				.andExpect(status().isOk());
	}

}
