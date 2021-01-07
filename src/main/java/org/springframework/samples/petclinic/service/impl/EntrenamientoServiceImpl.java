package org.springframework.samples.petclinic.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Entrenamiento;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.repository.EntrenamientoRepository;
import org.springframework.samples.petclinic.service.EntrenamientoService;
import org.springframework.samples.petclinic.service.EquipoService;
import org.springframework.samples.petclinic.service.EstadisticaPersonalEntrenamientoService;
import org.springframework.samples.petclinic.service.LineaMaterialService;
import org.springframework.samples.petclinic.service.base.impl.AbstractEstadisticasService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EntrenamientoServiceImpl extends AbstractEstadisticasService<Entrenamiento> implements EntrenamientoService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Log LOG = LogFactory.getLog(EntrenamientoServiceImpl.class);
	
	@Autowired
	private EntrenamientoRepository entrenamientoRepository;
	
	@Autowired
	private EntrenamientoService entrenamientoService;
	
	@Autowired
	private EquipoService equipoService;
	
	@Autowired
	private EstadisticaPersonalEntrenamientoService estadisticasService;
	
	@Autowired
	private LineaMaterialService lineaMaterialService;

	@Override
	public List<Entrenamiento> findByEquipoOrderByFecha(Equipo team) {
		return entrenamientoRepository.findByEquipoOrderByFecha(team);
	}

	@Override
	public List<Entrenamiento> findByFechaOrderByHora(LocalDate date) {
		return entrenamientoRepository.findByFechaOrderByHora(date);
	}

	@Override
	public List<Entrenamiento> findByEquipo(Equipo equipo) {
		
		return entrenamientoRepository.findByEquipo(equipo);
	}
	
	@Override
	@Transactional
	public void deleteById(Integer entrenamiento_id) {
		
		LOG.info("Se eliminarán las estadísticas del entrenamiento con id: " + entrenamiento_id);
		estadisticasService.deleteAllInEntrenamiento(entrenamiento_id);
		LOG.info("Se eliminarán las líneas de material del entrenamiento con id: " + entrenamiento_id);
		lineaMaterialService.deleteAllInEntrenamiento(entrenamiento_id);
		LOG.info("Borramos el partido");
		entrenamientoRepository.deleteById(entrenamiento_id);
		
	}

	@Override
	public void deleteAllInEquipo(Integer equipo_id) {
		Optional<Equipo> equipo = equipoService.findById(equipo_id);
		List<Entrenamiento> entrenamientos =entrenamientoRepository.findByEquipo(equipo.get());
		for(int i=0;i<entrenamientos.size();i++) {
			entrenamientoService.deleteById(entrenamientos.get(i).getId());
		}
		
	}
}
