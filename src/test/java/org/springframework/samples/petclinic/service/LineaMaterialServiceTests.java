package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.enumerate.EstadoMaterial;
import org.springframework.samples.petclinic.enumerate.TipoMaterial;
import org.springframework.samples.petclinic.model.Entrenamiento;
import org.springframework.samples.petclinic.model.LineaMaterial;
import org.springframework.samples.petclinic.model.Material;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class LineaMaterialServiceTests {

	@Autowired
	private LineaMaterialService lineaMaterialService;
	
	@Autowired
	private MaterialService materialService;
	
	@Autowired 
	private EntrenamientoService entrenamientoService;



	@Test
	@Transactional(readOnly = true)
	public void testFindAllInitialData() {
		List<LineaMaterial>lineamaterial=new ArrayList<LineaMaterial>(lineaMaterialService.findAll());
		assertEquals(lineamaterial.size(), 18);
	}



	@Test
	@Transactional(readOnly = true)
	public void testFindByIdInitialDataFinding() {
		int id=1;
		Optional<LineaMaterial> lineamaterial=lineaMaterialService.findById(id);
		assertNotNull(lineamaterial);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByIdInitialDataNotFinding() {
		int id=1000;
		Optional<LineaMaterial> lineamaterial=lineaMaterialService.findById(id);
		assertEquals(lineamaterial, Optional.empty());
	}

	@Test
	@Transactional(readOnly = true)
	public void testFindByCantidadInitialDataFinding() {
		int cantidad=5;
		List<LineaMaterial> lineamaterial = new ArrayList<LineaMaterial>(lineaMaterialService.findByCantidad(cantidad));
		assertEquals(lineamaterial.size(), 5);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByCantidadInitialDataNotFinding() {
		int cantidad=123;
		List<LineaMaterial> lineamaterial = new ArrayList<LineaMaterial>(lineaMaterialService.findByCantidad(cantidad));
		assertEquals(lineamaterial.size(), 0);
	}

	@Test
	@Transactional(readOnly = true)
	public void testFindByMaterialInitialDataFinding() {
		int material_id=4;
		List<LineaMaterial> lineamaterial=lineaMaterialService.findByMaterial(material_id);
		assertEquals(lineamaterial.size(), 3);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByMaterialInitialDataNotFinding() {
		int material_id = 6;
		List<LineaMaterial> lineamaterial=lineaMaterialService.findByMaterial(material_id);
		assertEquals(lineamaterial.size(),0);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByEntrenamientoInitialDataFinding() {
		int entrenamiento_id=1;
		List<LineaMaterial> lineamaterial=lineaMaterialService.findByEntrenamiento(entrenamiento_id);
		assertEquals(lineamaterial.size(), 5);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByEntrenamientoInitialDataNotFinding() {
		int entrenamiento_id=6;
		List<LineaMaterial> lineamaterial=lineaMaterialService.findByEntrenamiento(entrenamiento_id);
		assertEquals(lineamaterial.size(),0);
	}


	@Test
	@Transactional
	public void testSaveLineaMaterial() {

		
		Material material = new Material("cono", TipoMaterial.CONOALTO, 7);


		Entrenamiento entrenamiento = new Entrenamiento();
		LineaMaterial lineaMaterial = new LineaMaterial(material, entrenamiento, 8);

		LineaMaterial linea = lineaMaterialService.save(lineaMaterial);

		assertNotNull(linea);

		assertEquals(linea.getMaterial(), material);
		assertEquals(linea.getEntrenamiento(), entrenamiento);

	}
	
	@Test
	@Transactional
	public void testDeleteAllInEntrenamiento() {
		int id = 1;
		
		List<LineaMaterial> lineas = lineaMaterialService.findByEntrenamiento(id);
		int before = lineas.size();
		
		lineaMaterialService.deleteAllInEntrenamiento(id);
		
		List<LineaMaterial> after = lineaMaterialService.findByEntrenamiento(id);
		
		assertNotEquals(before, after.size());
		assertEquals(0, after.size());
		
	}
	
	@Test
	@Transactional
	public void testFindByTipoAndEstado() {
		TipoMaterial tipo = TipoMaterial.BALONDEJUEGO;
		EstadoMaterial estado = EstadoMaterial.ACEPTABLE;
		
		List<LineaMaterial> lineas = lineaMaterialService.findByTipoAndEstado(tipo, estado);
		
		assertEquals(5, lineas.size());
		assertEquals(TipoMaterial.BALONDEJUEGO, lineas.get(0).getMaterial().getTipo());
		
	}
	
	@Test
	@Transactional
	public void testFindByMaterialAndEntrenamiento() {
		Material material = materialService.findById(2).get();
		Entrenamiento entrenamiento = entrenamientoService.findById(1).get();
		
		List<LineaMaterial> lineas = lineaMaterialService.findByMaterialAndEntrenamiento(material, entrenamiento);
		
		assertEquals(1, lineas.size());
		assertEquals(TipoMaterial.BALONDEJUEGO, lineas.get(0).getMaterial().getTipo());
		
	}
}
