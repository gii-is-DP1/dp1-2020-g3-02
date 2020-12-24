package org.springframework.samples.petclinic.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.converter.PartidoConverter;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.Partido;
import org.springframework.samples.petclinic.model.auxiliares.PartidoConAsistencia;
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
	
	@Autowired
	private PartidoService partidoService;
	
	private static final Log LOG = LogFactory.getLog(PartidoServiceImpl.class);
	
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
	@Transactional
	public void deleteById(Integer partido_id) {
		
		LOG.info("Se eliminarán las estadísticas del partido con id: " + partido_id);
		estadisticasService.deleteAllInPartido(partido_id);
		LOG.info("Se eliminarán las sustituciones del partido con id: " + partido_id);
		sustitucionService.deleteAllInPartido(partido_id);
		LOG.info("Se eliminarán los viajes del partido con id: " + partido_id);
		viajeService.deleteAllInPartido(partido_id);
		LOG.info("Borramos el partido");
		partidoRepository.deleteById(partido_id);
		
	}

	@Override
	public List<Partido> findByEquipo(Equipo equipo) {
		
		return partidoRepository.findByEquipo(equipo);
	}

	@Override
	public List<PartidoConAsistencia> obtenerPartidosConfrontados(List<Equipo> equiposJugador, Jugador jugador, Partido partido) {
		PartidoConverter partidoConverter = new PartidoConverter();
		
		List<PartidoConAsistencia> partidosConfrontados = new ArrayList<PartidoConAsistencia>();
		PartidoConAsistencia partidoConfrontacion = new PartidoConAsistencia();
		
		String horaAnterior = horaMasMenosNHoras(partido.getHora(), -2);
		String horaPosterior = horaMasMenosNHoras(partido.getHora(), 2);
		
		for(int i=0;i<equiposJugador.size();i++) {
			
			List<Partido> partidosAsisteJugador = new ArrayList<Partido>(jugador.getPartidos());
			List<Partido> partidosAntes = partidoService.findByEquipoAndFechaAndHoraBetween(equiposJugador.get(i), partido.getFecha(), horaAnterior, partido.getHora());
			List<Partido> partidosDespues = partidoService.findByEquipoAndFechaAndHoraBetween(equiposJugador.get(i), partido.getFecha(), horaPosterior, partido.getHora());
			LOG.info(partidosAsisteJugador.size());
			partidosAsisteJugador.retainAll(partidosAntes);
			LOG.info(partidosAntes.size());
			LOG.info(partidosAsisteJugador.size());
			
			if(partidosAsisteJugador.size() != 0) {
				partidoConfrontacion = partidoConverter.convertPartidoToPartidoConAsistencia(partidosAsisteJugador.get(0));
				partidosConfrontados.add(partidoConfrontacion);
				
			}
			partidosAsisteJugador.retainAll(partidosDespues);
			if (partidosAsisteJugador.size() != 0) {
				partidoConfrontacion = partidoConverter.convertPartidoToPartidoConAsistencia(partidosAsisteJugador.get(0));
				partidosConfrontados.add(partidoConfrontacion);
			}
			
		}
		return partidosConfrontados;
	}
	
	private String horaMasMenosNHoras(String hora, int tiempo) {
		String[] splitHora = hora.split(":");
		Integer horaInt = Integer.valueOf(splitHora[0]);
		Integer minutosInt = Integer.valueOf(splitHora[1]);
		Integer horaAux = horaInt + tiempo;
		Integer minutosAux = 0;
		
		if(tiempo > 0) {
			minutosAux = minutosInt - 1;
			if(minutosAux < 0) {
				minutosAux = minutosAux + 60;
				horaAux = horaAux - 1;
			}
		} else {
			minutosAux = minutosInt + 1;
			if(minutosAux > 59) {
				minutosAux = minutosAux - 60;
				horaAux = horaAux + 1;
			}
		}
		
		if(horaAux < 0) {
			horaAux = horaAux + 24;
		} else if(horaAux > 23) {
			horaAux = horaAux - 24;
		}
		
		String horaString = (horaAux < 10)?"0"+horaAux:horaAux.toString();
		String minutosString = (minutosAux < 10)?"0"+minutosAux:minutosAux.toString();
		
		String horaFin = horaString+":"+minutosString;
		
		return horaFin;
	}

}
