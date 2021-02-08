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
import org.springframework.samples.petclinic.controller.MaterialController;
import org.springframework.samples.petclinic.enumerate.EstadoMaterial;
import org.springframework.samples.petclinic.enumerate.TipoMaterial;
import org.springframework.samples.petclinic.web.base.BaseControllerTest;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value = MaterialController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class))
public class MaterialControllerTest extends BaseControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@WithMockUser(value = "spring")
	@Test
	void testListadoMateriales() throws Exception {

		mockMvc.perform(get("/materiales/showmateriales"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("listestados"))
		.andExpect(view().name(ViewConstant.VIEW_MATERIALES));
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testListadoMaterialesEntrenamiento() throws Exception {

		mockMvc.perform(get("/materiales/showmaterialesentr"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("materiales"))
		.andExpect(view().name(ViewConstant.VIEW_MATERIALES_ENTRENAMIENTO));
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testTablaMaterial() throws Exception {

		mockMvc.perform(get("/materiales/tablamaterial"))
		.andExpect(jsonPath("$.data[0].tipo", is("BALONMEDICINAL")))
		.andExpect(status().isOk());
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testUpdateMaterial() throws Exception {
	
		mockMvc.perform(post("/materiales/updatematerial").with(csrf())
		.param("tipo", "BALONMEDICINAL")
		.param("EstadoAnterior", "NUEVO")
		.param("EstadoNuevo", "DAÑADO")
		.param("cantidad", "2"))
		.andExpect(status().isCreated());
	}
	
	
	@WithMockUser(value = "spring")
	@Test
	void testPostMaterial() throws Exception {
		
		mockMvc.perform(post("/materiales/postmaterial").with(csrf())
		.param("tipo", "BALONMEDICINAL")
		.param("cantidad", "2"))
		.andExpect(status().isCreated());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testPostMaterialwithMaterialNull() throws Exception {
		
		when(materialService.findByTipoAndEstado(any(TipoMaterial.class), any(EstadoMaterial.class))).thenReturn(null);
		
		mockMvc.perform(post("/materiales/postmaterial").with(csrf())
		.param("tipo", "BALONMEDICINAL")
		.param("cantidad", "2"))
		.andExpect(status().isCreated());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testRemoveMaterial() throws Exception {
	
		mockMvc.perform(post("/materiales/removeMaterial").with(csrf())
		.param("tipo", "BALONMEDICINAL")
		.param("EstadodelMat", "NUEVO")
		.param("cantidad", "2"))
		.andExpect(status().isCreated());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testRemoveMaterialwithMaterialNull() throws Exception {
		
		when(materialService.findByTipoAndEstado(any(TipoMaterial.class), any(EstadoMaterial.class))).thenReturn(null);
		
		mockMvc.perform(post("/materiales/removeMaterial").with(csrf())
		.param("tipo", "BALONMEDICINAL")
		.param("EstadodelMat", "NUEVO")
		.param("cantidad", "2"))
		.andExpect(status().isCreated());
	}
	
	
	
}
