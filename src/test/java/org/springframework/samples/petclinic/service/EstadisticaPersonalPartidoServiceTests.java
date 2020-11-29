package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.enumerate.Posicion;
import org.springframework.samples.petclinic.enumerate.TipoPrueba;
import org.springframework.samples.petclinic.model.EstadisticaPersonalPartido;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.PruebaCondicionFisica;
import org.springframework.samples.petclinic.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class EstadisticaPersonalPartidoServiceTests {
	
	@Autowired
	@Qualifier("estadisticaPersonalPartidoService")
	private EstadisticaPersonalPartidoService ePartidoService;
	
	@Test
	@Transactional(readOnly = true)
	public void testFindAllInitialDataFinding() {
		List<EstadisticaPersonalPartido> ePartido = ePartidoService.findAll();
		assertNotNull(ePartido);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByIdInitialDataFinding() {
		int id = 1;
		Optional<EstadisticaPersonalPartido> ePartido = ePartidoService.findById(id);
		assertNotNull(ePartido);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByIdInitialDataNotFinding() {
		int id = 50;
		Optional<EstadisticaPersonalPartido> ePartido = ePartidoService.findById(id);
		assertEquals(ePartido, Optional.empty());
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeSaquesLessThanEqualInitialDataFinding() {
		double percent = 0.8;
		List<EstadisticaPersonalPartido> ePartido = ePartidoService.findByPorcentajeSaquesLessThanEqual(percent);
		assertNotNull(ePartido);
	}
	
	@Test
	public void testFindByPorcentajeSaquesLessThanEqualInitialDataNotFinding() {
		double percent = 0.2;
		List<EstadisticaPersonalPartido> ePartido = ePartidoService.findByPorcentajeSaquesLessThanEqual(percent);
		assertEquals(ePartido.size(), 0);
	}
	
	@Test
	public void testFindByPorcentajeRecepcionesLessThanEqualInitialDataFinding() {
		double percentIntroducido=50;
		double percent=percentIntroducido/100;
		List<EstadisticaPersonalPartido> ePartido=ePartidoService.findByPorcentajeRecepcionesLessThanEqual(percent);
		assertEquals(ePartido.size(), 15);
	}
	
	@Test
	public void testFindByPorcentajeRecepcionesLessThanEqualInitialDataNotFinding() {
		double percentIntroducido=-50;
		double percent=percentIntroducido/100;
		List<EstadisticaPersonalPartido> ePartido=new ArrayList<EstadisticaPersonalPartido>(ePartidoService.findByPorcentajeRecepcionesLessThanEqual(percent));
		assertEquals(ePartido.size(), 0);
	}
	
	@Test
	public void testFindByPorcentajeColocacionesLessThanEqualInitialDataFinding() {
		double percentIntroducido=50;
		double percent=percentIntroducido/100;
		List<EstadisticaPersonalPartido> ePartido=new ArrayList<EstadisticaPersonalPartido>(ePartidoService.findByPorcentajeColocacionesLessThanEqual(percent));
		assertEquals(ePartido.size(), 15);
	}
	
	@Test
	public void testFindByPorcentajeColocacionesLessThanEqualInitialDataNotFinding() {
		double percentIntroducido=-50;
		double percent=percentIntroducido/100;
		List<EstadisticaPersonalPartido> ePartido=new ArrayList<EstadisticaPersonalPartido>(ePartidoService.findByPorcentajeColocacionesLessThanEqual(percent));
		assertEquals(ePartido.size(), 0);
	}
	
	@Test
	public void testFindByPorcentajeDefensasLessThanEqualInitialDataFinding() {
		double percentIntroducido=50;
		double percent=percentIntroducido/100;
		List<EstadisticaPersonalPartido> ePartido=new ArrayList<EstadisticaPersonalPartido>(ePartidoService.findByPorcentajeDefensasLessThanEqual(percent));
		assertEquals(ePartido.size(), 15);
	}
	
	@Test
	public void testFindByPorcentajeDefensasLessThanEqualInitialDataNotFinding() {
		double percentIntroducido=-50;
		double percent=percentIntroducido/100;
		List<EstadisticaPersonalPartido> ePartido=new ArrayList<EstadisticaPersonalPartido>(ePartidoService.findByPorcentajeDefensasLessThanEqual(percent));
		assertEquals(ePartido.size(), 0);
	}
	@Test
	public void testFindByPorcentajeBloqueosLessThanEqualInitialDataFinding() {
		double percentIntroducido=50;
		double percent=percentIntroducido/100;
		List<EstadisticaPersonalPartido> ePartido=new ArrayList<EstadisticaPersonalPartido>(ePartidoService.findByPorcentajeBloqueosLessThanEqual(percent));
		assertEquals(ePartido.size(), 18);
	}
	
	@Test
	public void testFindByPorcentajeBloqueosLessThanEqualInitialDataNotFinding() {
		double percentIntroducido=-50;
		double percent=percentIntroducido/100;
		List<EstadisticaPersonalPartido> ePartido=new ArrayList<EstadisticaPersonalPartido>(ePartidoService.findByPorcentajeBloqueosLessThanEqual(percent));
		assertEquals(ePartido.size(), 0);
	}
	
	@Test
	public void testFindByPorcentajeRematesLessThanEqualInitialDataFinding() {
		double percentIntroducido=50;
		double percent=percentIntroducido/100;
		List<EstadisticaPersonalPartido> ePartido=new ArrayList<EstadisticaPersonalPartido>(ePartidoService.findByPorcentajeRematesLessThanEqual(percent));
		assertEquals(ePartido.size(), 15);
	}
	
	@Test
	public void testFindByPorcentajeRematesLessThanEqualInitialDataNotFinding() {
		double percentIntroducido=-50;
		double percent=percentIntroducido/100;
		List<EstadisticaPersonalPartido> ePartido=new ArrayList<EstadisticaPersonalPartido>(ePartidoService.findByPorcentajeRematesLessThanEqual(percent));
		assertEquals(ePartido.size(), 0);
	}
	
	@Test
	public void testFindByPorcentajeFintasLessThanEqualInitialDataFinding() {
		double percentIntroducido=50;
		double percent=percentIntroducido/100;
		List<EstadisticaPersonalPartido> ePartido=new ArrayList<EstadisticaPersonalPartido>(ePartidoService.findByPorcentajeFintasLessThanEqual(percent));
		assertEquals(ePartido.size(), 18);
	}
	
	@Test
	public void testFindByPorcentajeFintasLessThanEqualInitialDataNotFinding() {
		double percentIntroducido=-50;
		double percent=percentIntroducido/100;
		List<EstadisticaPersonalPartido> ePartido=new ArrayList<EstadisticaPersonalPartido>(ePartidoService.findByPorcentajeFintasLessThanEqual(percent));
		assertEquals(ePartido.size(), 0);
	}
	
	@Test
	public void testFindByPorcentajeAtaquesRapidosLessThanEqualInitialDataFinding() {
		double percentIntroducido=50;
		double percent=percentIntroducido/100;
		List<EstadisticaPersonalPartido> ePartido=new ArrayList<EstadisticaPersonalPartido>(ePartidoService.findByPorcentajeAtaquesRapidosLessThanEqual(percent));
		assertEquals(ePartido.size(), 15);
	}
	
	@Test
	public void testFindByPorcentajeAtaquesRapidosLessThanEqualInitialDataNotFinding() {
		double percentIntroducido=-50;
		double percent=percentIntroducido/100;
		List<EstadisticaPersonalPartido> ePartido=new ArrayList<EstadisticaPersonalPartido>(ePartidoService.findByPorcentajeAtaquesRapidosLessThanEqual(percent));
		assertEquals(ePartido.size(), 0);
	}
	
	

}
