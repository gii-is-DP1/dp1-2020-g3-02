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
import org.springframework.samples.petclinic.enumerate.TipoPrivilegio;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.Privilegio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class PrivilegioServiceTest {
	
	@Autowired
	private PrivilegioService privilegioService;
	
	@Autowired
	private JugadorService jugadorService;
	
	@Autowired
	private EquipoService equipoService;
	
	@Test
	@Transactional(readOnly = true)
	public void testFindAllInitialData() {
		List<Privilegio> privilegios = new ArrayList<Privilegio>(privilegioService.findAll());
		assertEquals(privilegios.size(),1);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByIdInitialDataFinding() {
		int id=1;
		Optional<Privilegio> privilegio=privilegioService.findById(id);
		assertNotNull(privilegio);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByIdInitialDataNotFinding() {
		int id=1000;
		Optional<Privilegio> privilegio=privilegioService.findById(id);
		assertEquals(privilegio, Optional.empty());
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByDescripcionInitialDataFinding() {
		String descripcion= "de locos";
		List<Privilegio> privilegio = new ArrayList<Privilegio>(privilegioService.findByDescripcion(descripcion));
		assertEquals(privilegio.size(), 1);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByDescripcionInitialDataNotFinding() {
		String descripcion= "de cuerdos";
		List<Privilegio> privilegio = new ArrayList<Privilegio>(privilegioService.findByDescripcion(descripcion));
		assertEquals(privilegio.size(), 0);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByTipoPrivilegioInitialDataFinding() {
		TipoPrivilegio tipo = TipoPrivilegio.PARTIDOS;
		List<Privilegio> privilegio = new ArrayList<Privilegio>(privilegioService.findByTipoPrivilegio(tipo));
		assertEquals(privilegio.size(), 1);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByTipoPrivilegioInitialDataNotFinding() {
		TipoPrivilegio tipo = TipoPrivilegio.ENTRENAMIENTOS;
		List<Privilegio> privilegio = new ArrayList<Privilegio>(privilegioService.findByTipoPrivilegio(tipo));
		assertEquals(privilegio.size(), 0);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByJugadorInitialDataFinding() {
		int id = 1;
		Optional<Jugador> jugador = jugadorService.findById(id);
		List<Privilegio> privilegio = new ArrayList<Privilegio>(privilegioService.findByJugador(jugador.get()));
		assertEquals(privilegio.size(), 1);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByJugadorInitialDataNotFinding() {
		int id = 2;
		Optional<Jugador> jugador = jugadorService.findById(id);
		List<Privilegio> privilegio = new ArrayList<Privilegio>(privilegioService.findByJugador(jugador.get()));
		assertEquals(privilegio.size(), 0);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByEquipoInitialDataFinding() {
		int id = 1;
		Optional<Equipo> equipo = equipoService.findById(id);
		List<Privilegio> privilegio = new ArrayList<Privilegio>(privilegioService.findByEquipo(equipo.get()));
		assertEquals(privilegio.size(), 1);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByEquipoInitialDataNotFinding() {
		int id = 2;
		Optional<Equipo> equipo = equipoService.findById(id);
		List<Privilegio> privilegio = new ArrayList<Privilegio>(privilegioService.findByEquipo(equipo.get()));
		assertEquals(privilegio.size(), 0);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testUpdatePrivilegio() {
		
		Optional<Jugador> jugador= jugadorService.findById(3);
		Jugador player= jugador.get();
		
		Optional<Equipo> equipo= equipoService.findById(2);
		Equipo team= equipo.get();
		
		String descripcion = "de cuerdos";
		
		Privilegio privilegio = new Privilegio(player, team, TipoPrivilegio.PARTIDOS, descripcion);	

		Privilegio priv = privilegioService.updatePrivilegio(privilegio);

		assertNotNull(priv);
		
		assertEquals(priv.getJugador(), player);
		assertEquals(priv.getDescripcion(), "de cuerdos");
		assertEquals(priv.getEquipo(), team);
		
	}
	
	@Test
	@Transactional
	public void testSavePrivilegios() {
		
		Optional<Jugador> jugador= jugadorService.findById(3);
		Jugador player= jugador.get();
		
		Optional<Equipo> equipo= equipoService.findById(2);
		Equipo team= equipo.get();
		
		String descripcion = "de cuerdos";
		
		Privilegio privilegio = new Privilegio(player, team, TipoPrivilegio.PARTIDOS, descripcion);	

		Privilegio priv = privilegioService.save(privilegio);

		assertNotNull(priv);
		
		assertEquals(priv.getJugador(), player);
		assertEquals(priv.getDescripcion(), "de cuerdos");
		assertEquals(priv.getEquipo(), team);

	}
	
	@Test
	@Transactional
	public void testDeleteAllInEquipo() {
		
		int totalesAntes = privilegioService.findAll().size();
		int equipo = privilegioService.findByEquipo(equipoService.findById(1).get()).size();
		privilegioService.deleteAllInEquipo(1);

		assertEquals(privilegioService.findAll().size(),totalesAntes-equipo);

	}

}

