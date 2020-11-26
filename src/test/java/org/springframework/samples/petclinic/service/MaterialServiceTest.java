package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.enumerate.TipoMaterial;
import org.springframework.samples.petclinic.model.Material;
import org.springframework.samples.petclinic.model.NumCamiseta;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class MaterialServiceTest {

	@Autowired
	private MaterialService materialService;



	@Test
	public void testFindAllInitialData() {
		List<Material> material = new ArrayList<Material>(materialService.findAll());
		assertEquals(material.size(), 6);//
	}

	@Test
	public void testFindByIdInitialDataFinding() {
		int id = 1;
		Optional<Material> material = materialService.findById(id);
		assertNotNull(material);
	}
	
	@Test
	public void testFindByIdInitialDataNotFinding() {
		int id = 1000;
		Optional<Material> material = materialService.findById(id);
		assertEquals(material, Optional.empty());
	}
	
	@Test
	public void testFindByTipoInitialDataFinding() {
		TipoMaterial tipo = TipoMaterial.CUERDA;
		List<Material> material = materialService.findByTipo(tipo);
		assertEquals(material.size(), 1);
	}
	
	@Test
	public void testFindByTipoInitialDataNotFinding() {
		TipoMaterial tipo = TipoMaterial.RED;
		List<Material> material = materialService.findByTipo(tipo);
		assertEquals(material.size(), 0);
	}
	
	@Test
	public void testFindByDescripcionInitialDataFinding() {
		String description = "poste";
		List<Material> material = materialService.findByDescripcion(description);
		assertEquals(material.size(), 2);
	}
	
	@Test
	public void testFindByDescripcionInitialDataNotFinding() {
		String description = "asdjnrfu";
		List<Material> material = materialService.findByDescripcion(description);
		assertEquals(material.size(), 0);
	}
	
	@Test
	public void testFindByStockInitialDataFinding() {
		int stock = 9;
		List<Material> material = materialService.findByStock(stock);
		assertEquals(material.size(), 1);
	}
	
	@Test
	public void testFindByStockInitialDataNotFinding() {
		int stock = 1000;
		List<Material> material = materialService.findByStock(stock);
		assertEquals(material.size(), 0);
	}
	
	@Test
	public void testFindByLineaMaterialInitialDataFinding() {
		int linea_material_id = 1;
		List<Material> material = new ArrayList<Material>(materialService.findByLineaMaterial(linea_material_id));
		assertEquals(material.size(), 2);
	}
	
	@Test
	public void testFindByLineaMaterialInitialDataNotFinding() {
		int linea_material_id = 5;
		List<Material> material = new ArrayList<Material>(materialService.findByLineaMaterial(linea_material_id));
		assertEquals(material.size(), 0);
	}
	
	@Test
	public void testSaveMaterial() {
		Material material = new Material("red",TipoMaterial.RED,5);	

		Material _material = materialService.saveMaterial(material);

		assertNotNull(_material);
	}
}
