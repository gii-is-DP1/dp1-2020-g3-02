package org.springframework.samples.petclinic.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.converter.EntrenamientoConverter;
import org.springframework.samples.petclinic.model.Entrenamiento;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.auxiliares.EntrenamientoConAsistencia;
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
	public List<Entrenamiento> findByFechaAfter(LocalDate date) {
		return entrenamientoRepository.findByFechaAfter(date);
	}
	
	@Override
	public List<Entrenamiento> findByEquipoAndFechaAndHoraBetween(Equipo equipo, LocalDate fecha, String hora1, String hora2) {
		return entrenamientoRepository.findByEquipoAndFechaAndHoraBetween(equipo, fecha, hora1, hora2);
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
		LOG.info("Borramos el entrenamiento");
		entrenamientoRepository.deleteById(entrenamiento_id);
		
	}
	
	@Override
	public List<EntrenamientoConAsistencia> obtenerEntrenamientosAsistidos(List<Equipo> equiposJ, Jugador jugador, Entrenamiento entrenamiento) {
		EntrenamientoConverter entrenamientoConverter = new EntrenamientoConverter();
		
		List<EntrenamientoConAsistencia> entrenamientosAsistidos = new ArrayList<EntrenamientoConAsistencia>();
		EntrenamientoConAsistencia entrenamientoAsistido = new EntrenamientoConAsistencia();
		
		String horaAnterior = horaMasMenosNHoras(entrenamiento.getHora(), -1);
		String horaPosterior = horaMasMenosNHoras(entrenamiento.getHora(), 1);
		
		for(int i=0;i<equiposJ.size();i++) {
			
			List<Entrenamiento> entrenamientosAsisteJugador = new ArrayList<Entrenamiento>(jugador.getEntrenamientos());
			List<Entrenamiento> entrenamientosAntes = entrenamientoService.findByEquipoAndFechaAndHoraBetween(equiposJ.get(i), 
					entrenamiento.getFecha(), horaAnterior, entrenamiento.getHora());
			List<Entrenamiento> entrenamientosDespues = entrenamientoService.findByEquipoAndFechaAndHoraBetween(equiposJ.get(i), 
					entrenamiento.getFecha(), horaPosterior, entrenamiento.getHora());
			LOG.info(entrenamientosAsisteJugador.size());
			entrenamientosAsisteJugador.retainAll(entrenamientosAntes);
			LOG.info(entrenamientosAntes.size());
			LOG.info(entrenamientosAsisteJugador.size());
			
			if(entrenamientosAsisteJugador.size() != 0) {
				entrenamientoAsistido = entrenamientoConverter.convertEntrenamientoToEntrenamientoConAsistencia(entrenamientosAsisteJugador.get(0));
				entrenamientosAsistidos.add(entrenamientoAsistido);
				
			}
			entrenamientosAsisteJugador.retainAll(entrenamientosDespues);
			if (entrenamientosAsisteJugador.size() != 0) {
				entrenamientoAsistido = entrenamientoConverter.convertEntrenamientoToEntrenamientoConAsistencia(entrenamientosAsisteJugador.get(0));
				entrenamientosAsistidos.add(entrenamientoAsistido);
			}
			
		}
		return entrenamientosAsistidos;
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

	@Override
	public void deleteAllInEquipo(Integer equipo_id) {
		Optional<Equipo> equipo = equipoService.findById(equipo_id);
		List<Entrenamiento> entrenamientos =entrenamientoRepository.findByEquipo(equipo.get());
		for(int i=0;i<entrenamientos.size();i++) {
			entrenamientoService.deleteById(entrenamientos.get(i).getId());
		}
		
	}
}
