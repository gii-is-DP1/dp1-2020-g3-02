package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.enumerate.Sistema;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class EquipoServiceTest {
	
	@Autowired
	private EquipoService equipoService;
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByIdDataFinding() {
		int id = 1;
		Optional<Equipo> equipo = equipoService.findById(id);
		assertNotEquals(equipo.get(), Optional.empty());
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByIdDataNotFinding() {
		int id = 1000;
		Optional<Equipo> equipo = equipoService.findById(id);
		assertEquals(equipo, Optional.empty());
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindAll() {
		List<Equipo> equipos = equipoService.findAll();
		assertEquals(equipos.size(), 3);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindCategoria() {
		List<String> categorias = equipoService.findCategoria();
		assertEquals(categorias.size(), 3);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByCategoriaDataFinding() {
		String categoria = "Senior";
		Equipo equipo = equipoService.findByCategoria(categoria);
		assertNotNull(equipo);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByCategoriaDataNotFinding() {
		String categoria = "YYYYYYY";
		Equipo equipo = equipoService.findByCategoria(categoria);
		assertNull(equipo);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByCategoriaStartingWithDataFinding() {
		String categoria = "Sen";
		List<Equipo> equipos = equipoService.findByCategoriaStartingWith(categoria);
		assertEquals(equipos.size(), 1);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByCategoriaStartingWithDataNotFinding() {
		String categoria = "YYYYYYY";
		List<Equipo> equipos = equipoService.findByCategoriaStartingWith(categoria);
		assertEquals(equipos.size(), 0);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByLigaDataFinding() {
		String liga = "IMD";
		List<Equipo> equipos = equipoService.findByLiga(liga);
		assertEquals(equipos.size(), 3);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByLigaDataNotFinding() {
		String liga = "YYYYYYY";
		List<Equipo> equipos = equipoService.findByLiga(liga);
		assertEquals(equipos.size(), 0);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeSaquesLessThanEqualDataFinding() {
		double percentIntroducido=100;
		double percent=percentIntroducido/100;
		List<Equipo> equipos = equipoService.findByPorcentajeSaquesLessThanEqual(percent);
		assertEquals(equipos.size(), 3);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeSaquesLessThanEqualDataNotFinding() {
		double percentIntroducido=-100;
		double percent=percentIntroducido/100;
		List<Equipo> equipos = equipoService.findByPorcentajeSaquesLessThanEqual(percent);
		assertEquals(equipos.size(), 0);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeRecepcionesLessThanEqualDataFinding() {
		double percentIntroducido=100;
		double percent=percentIntroducido/100;
		List<Equipo> equipos = equipoService.findByPorcentajeRecepcionesLessThanEqual(percent);
		assertEquals(equipos.size(), 3);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeRecepcionesLessThanEqualDataNotFinding() {
		double percentIntroducido=-100;
		double percent=percentIntroducido/100;
		List<Equipo> equipos = equipoService.findByPorcentajeRecepcionesLessThanEqual(percent);
		assertEquals(equipos.size(), 0);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeColocacionesLessThanEqualDataFinding() {
		double percentIntroducido=100;
		double percent=percentIntroducido/100;
		List<Equipo> equipos = equipoService.findByPorcentajeColocacionesLessThanEqual(percent);
		assertEquals(equipos.size(), 3);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeColocacionesLessThanEqualDataNotFinding() {
		double percentIntroducido=-100;
		double percent=percentIntroducido/100;
		List<Equipo> equipos = equipoService.findByPorcentajeColocacionesLessThanEqual(percent);
		assertEquals(equipos.size(), 0);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeDefensasLessThanEqualDataFinding() {
		double percentIntroducido=100;
		double percent=percentIntroducido/100;
		List<Equipo> equipos = equipoService.findByPorcentajeDefensasLessThanEqual(percent);
		assertEquals(equipos.size(), 3);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeDefensasLessThanEqualDataNotFinding() {
		double percentIntroducido=-100;
		double percent=percentIntroducido/100;
		List<Equipo> equipos = equipoService.findByPorcentajeDefensasLessThanEqual(percent);
		assertEquals(equipos.size(), 0);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeBloqueosLessThanEqualDataFinding() {
		double percentIntroducido=100;
		double percent=percentIntroducido/100;
		List<Equipo> equipos = equipoService.findByPorcentajeColocacionesLessThanEqual(percent);
		assertEquals(equipos.size(), 3);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeBloqueosLessThanEqualDataNotFinding() {
		double percentIntroducido=-100;
		double percent=percentIntroducido/100;
		List<Equipo> equipos = equipoService.findByPorcentajeBloqueosLessThanEqual(percent);
		assertEquals(equipos.size(), 0);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeRematesLessThanEqualDataFinding() {
		double percentIntroducido=100;
		double percent=percentIntroducido/100;
		List<Equipo> equipos = equipoService.findByPorcentajeBloqueosLessThanEqual(percent);
		assertEquals(equipos.size(), 3);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeRematesLessThanEqualDataNotFinding() {
		double percentIntroducido=-100;
		double percent=percentIntroducido/100;
		List<Equipo> equipos = equipoService.findByPorcentajeRematesLessThanEqual(percent);
		assertEquals(equipos.size(), 0);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeFintasLessThanEqualDataFinding() {
		double percentIntroducido=100;
		double percent=percentIntroducido/100;
		List<Equipo> equipos = equipoService.findByPorcentajeFintasLessThanEqual(percent);
		assertEquals(equipos.size(), 3);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeFintasLessThanEqualDataNotFinding() {
		double percentIntroducido=-100;
		double percent=percentIntroducido/100;
		List<Equipo> equipos = equipoService.findByPorcentajeFintasLessThanEqual(percent);
		assertEquals(equipos.size(), 0);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeAtaquesRapidosLessThanEqualDataFinding() {
		double percentIntroducido=100;
		double percent=percentIntroducido/100;
		List<Equipo> equipos = equipoService.findByPorcentajeAtaquesRapidosLessThanEqual(percent);
		assertEquals(equipos.size(), 3);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeAtaquesRapidosLessThanEqualDataNotFinding() {
		double percentIntroducido=-100;
		double percent=percentIntroducido/100;
		List<Equipo> equipos = equipoService.findByPorcentajeAtaquesRapidosLessThanEqual(percent);
		assertEquals(equipos.size(), 0);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByNumFaltasTotalesGreaterThanEqualDataFinding() {
		int faults=0;
		List<Equipo> equipos=new ArrayList<Equipo>(equipoService.findByNumFaltasTotalesGreaterThanEqual(faults));
		assertEquals(equipos.size(), 3);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByNumFaltasTotalesGreaterThanEqualDataNotFinding() {
		int faults=100;
		List<Equipo> equipos=new ArrayList<Equipo>(equipoService.findByNumFaltasTotalesGreaterThanEqual(faults));
		assertEquals(equipos.size(), 0);
	}
	
	@Test
	@Transactional
	public void testSaveTeam() {
		Equipo equipo = new Equipo();
		equipo.setCategoria("Alevín");
		equipo.setLiga("IMD");
		equipo.setSistemaJuego(Sistema.CINCO_UNO);
		
		Equipo team = equipoService.save(equipo);
		assertNotNull(team);
	}
	
//	@Test
//	@Transactional
//	public void testDeleteTeam() {
//		int id = 1;
//		equipoService.deleteTeam(id);
//		Optional<Equipo> equipo = equipoService.findById(id);
//		assertEquals(equipo, Optional.empty());
//	}

}
