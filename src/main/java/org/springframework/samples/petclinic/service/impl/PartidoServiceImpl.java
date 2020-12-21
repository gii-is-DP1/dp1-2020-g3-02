package org.springframework.samples.petclinic.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.EstadisticaPersonalPartido;
import org.springframework.samples.petclinic.model.Partido;
import org.springframework.samples.petclinic.model.Sustitucion;
import org.springframework.samples.petclinic.model.Viaje;
import org.springframework.samples.petclinic.repository.PartidoRepository;
import org.springframework.samples.petclinic.service.EstadisticaPersonalPartidoService;
import org.springframework.samples.petclinic.service.PartidoService;
import org.springframework.samples.petclinic.service.SustitucionService;
import org.springframework.samples.petclinic.service.ViajeService;
import org.springframework.samples.petclinic.service.base.impl.AbstractEstadisticasService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PartidoServiceImpl extends AbstractEstadisticasService<Partido> implements PartidoService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private PartidoRepository partidoRepository;
	
	@Autowired
	private EstadisticaPersonalPartidoService estadisticasService;
	
	@Autowired
	private SustitucionService sustitucionService;
	
	@Autowired
	private ViajeService viajeService;
	@Override
	public List<Partido> findByFechaOrderByHora(LocalDate date) {
		return partidoRepository.findByFechaOrderByHora(date);
	}

	@Override
	public List<Partido> findByFechaAfter(LocalDate date) {
		return partidoRepository.findByFechaAfter(date);
	}

	@Override
	public List<Partido> findByEquipoAndFechaAndHoraBetween(Equipo equipo, LocalDate fecha, String hora1, String hora2) {
		return partidoRepository.findByEquipoAndFechaAndHoraBetween(equipo, fecha, hora1, hora2);
	}

	@Override
	public Partido savePartido(Partido partido) {
		return partidoRepository.save(partido);
	}

	@Override
	@Transactional
	public void deleteById(Integer partido_id) {
		
		estadisticasService.deleteAllInPartido(partido_id);
		sustitucionService.deleteAllInPartido(partido_id);
		viajeService.deleteAllInPartido(partido_id);
		partidoRepository.deleteById(partido_id);
		
	}

	@Override
	public List<Partido> findByEquipo(Equipo equipo) {
		
		return partidoRepository.findByEquipo(equipo);
	}

}
