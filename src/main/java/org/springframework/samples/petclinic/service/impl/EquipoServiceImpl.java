package org.springframework.samples.petclinic.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.repository.EquipoRepository;
import org.springframework.samples.petclinic.service.EntrenamientoService;
import org.springframework.samples.petclinic.service.EquipoService;
import org.springframework.samples.petclinic.service.JugadorService;
import org.springframework.samples.petclinic.service.NumCamisetaService;
import org.springframework.samples.petclinic.service.PartidoService;
import org.springframework.samples.petclinic.service.PrivilegioService;
import org.springframework.samples.petclinic.service.base.impl.AbstractEstadisticasService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("equipoService")
public class EquipoServiceImpl extends AbstractEstadisticasService<Equipo> implements EquipoService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Log LOG = LogFactory.getLog(EquipoServiceImpl.class);
	
	@Autowired
	@Qualifier("equipoRepository")
	private EquipoRepository equipoRepository;
	
	@Autowired
	private JugadorService jugadorService;
	
	@Autowired
	private PartidoService partidoService;
	
	@Autowired
	private PrivilegioService privilegioService;
	
	@Autowired
	private EntrenamientoService entrenamientoService;
	
	@Autowired
	private NumCamisetaService numCamisetaService;
	
	@Override
	public List<String> findCategoria() {
		return equipoRepository.findCategoria();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Equipo findByCategoria(String category) {
		return equipoRepository.findByCategoria(category);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Equipo> findByCategoriaStartingWith(String category) {
		return equipoRepository.findByCategoriaStartingWith(category);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Equipo> findByLiga(String league) {
		return equipoRepository.findByLiga(league);
	}

	@Override
	public List<Jugador> findJugadoresNoEquipo(int id) {
		Optional<Equipo> equipo = equipoRepository.findById(id);
		List<Jugador> jugadoresEquipo = equipo.get().getJugadores();
		List<Jugador> jugadoresNoEquipo = jugadorService.findAll();
		jugadoresNoEquipo.removeAll(jugadoresEquipo);
		
		return jugadoresNoEquipo;
		
	}
	
	@Override
	@Transactional
	public void deleteById(Integer equipo_id) {
		
		LOG.info("Se eliminarán los partidos del equipo con id: " + equipo_id);
		partidoService.deleteAllInEquipo(equipo_id);
		LOG.info("Se eliminarán los entrenamientos del equipo con id: " + equipo_id);
		entrenamientoService.deleteAllInEquipo(equipo_id);
		LOG.info("Se eliminarán los numeros de camiseta del equipo con id: " + equipo_id);
		numCamisetaService.deleteAllInEquipo(equipo_id);
		LOG.info("Se eliminarán los privilegios del equipo con id: " + equipo_id);
		privilegioService.deleteAllInEquipo(equipo_id);
		LOG.info("Borramos el equipo");
		equipoRepository.deleteById(equipo_id);
		
	}

	@Override
	public Equipo updateEquipo(Equipo team) {
		// TODO Auto-generated method stub
		/** CÁLCULO DE PORCENTAJE SIEMPRE QUE SE ACTUALIZA UN EQUIPO */
		if(team.getSaquesTotales() > 0) team.setPorcentajeSaques(1.*team.getSaquesAcertados()/team.getSaquesTotales());
		if(team.getRecepcionesTotales() > 0) team.setPorcentajeRecepciones(1.*team.getRecepcionesAcertadas()/team.getRecepcionesTotales());
		if(team.getColocacionesTotales() > 0) team.setPorcentajeColocaciones(1.*team.getColocacionesAcertadas()/team.getColocacionesTotales());
		if(team.getDefensasTotales() > 0) team.setPorcentajeDefensas(1.*team.getDefensasAcertadas()/team.getDefensasTotales());
		if(team.getBloqueosTotales() > 0) team.setPorcentajeBloqueos(1.*team.getBloqueosAcertados()/team.getBloqueosTotales());
		if(team.getRematesTotales() > 0) team.setPorcentajeRemates(1.*team.getRematesAcertados()/team.getRematesTotales());
		if(team.getFintasTotales() > 0) team.setPorcentajeFintas(1.*team.getFintasAcertadas()/team.getFintasTotales());
		if(team.getNumAtaquesRapidosTotales() > 0) team.setPorcentajeAtaquesRapidos(1.*team.getNumAtaquesRapidosAcertados()/team.getNumAtaquesRapidosTotales());
		
		Equipo equipo=equipoRepository.save(team);
		
		return equipo;
	}

}
