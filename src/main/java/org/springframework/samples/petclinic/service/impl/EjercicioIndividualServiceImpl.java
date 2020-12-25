package org.springframework.samples.petclinic.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.samples.petclinic.enumerate.Posicion;
import org.springframework.samples.petclinic.enumerate.TipoEjercicio;
import org.springframework.samples.petclinic.model.EjercicioIndividual;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.repository.EjercicioIndividualRepository;
import org.springframework.samples.petclinic.service.EjercicioIndividualService;
import org.springframework.samples.petclinic.service.base.impl.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EjercicioIndividualServiceImpl extends AbstractService<EjercicioIndividual> implements EjercicioIndividualService {
	
	private static final Log LOG = LogFactory.getLog(EjercicioIndividualServiceImpl.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	@Qualifier("ejercicioIndividualRepository")
	private EjercicioIndividualRepository ejercicioIndividualRepository;
	
	@Override
	@Transactional(readOnly = true)
	public Optional<EjercicioIndividual> findByNombre(String nombre) {
		return ejercicioIndividualRepository.findByNombre(nombre);
	}

	@Override
	@Transactional(readOnly = true)
	public List<EjercicioIndividual> findByNombreContaining(String nombre) {
		return ejercicioIndividualRepository.findByNombreContaining(nombre);
	}

	@Override
	@Transactional(readOnly = true)
	public List<EjercicioIndividual> findByTipoEjercicio(TipoEjercicio tipo_ejercicio) {
		return ejercicioIndividualRepository.findByTipoEjercicio(tipo_ejercicio);
	}

	@Override
	public List<EjercicioIndividual> findEjerciciosRecomendados(Jugador jugador) {
		List<EjercicioIndividual> recomendados = new ArrayList<EjercicioIndividual>();
		
		//Si el jugador tiene menos porcentaje de saque del mínimo requerido
		if(jugador.getPorcentajeSaques() <= 0.70) {
			LOG.info("El jugador requiere ejercicios de saque");
			recomendados.addAll(ejercicioIndividualRepository.findByTipoEjercicio(TipoEjercicio.SAQUE));
		}
		
		//Si el jugador tiene menos porcentaje de recepción del mínimo requerido
		if(jugador.getPorcentajeRecepciones() <= 0.60
				|| (jugador.getPosicionPrincipal().equals(Posicion.LIBERO) && jugador.getPorcentajeRecepciones() <= 0.85)
				|| (jugador.getPosicionPrincipal().equals(Posicion.PUNTA) && jugador.getPorcentajeRecepciones() <= 0.75)) {
			LOG.info("El jugador requiere ejercicios de recepción");
			recomendados.addAll(ejercicioIndividualRepository.findByTipoEjercicio(TipoEjercicio.RECEPCION));
		}
		
		//Si el jugador tiene menos porcentaje de colocación del mínimo requerido
		if(jugador.getPorcentajeColocaciones() <= 0.40
				|| (jugador.getPosicionPrincipal().equals(Posicion.COLOCADOR) && jugador.getPorcentajeColocaciones() <= 0.85)) {
			LOG.info("El jugador requiere ejercicios de colocación");
			recomendados.addAll(ejercicioIndividualRepository.findByTipoEjercicio(TipoEjercicio.COLOCACION));
		}
		
		//Si el jugador tiene menos porcentaje de defensa del mínimo requerido
		if(jugador.getPorcentajeDefensas() <= 0.50
				|| (jugador.getPosicionPrincipal().equals(Posicion.LIBERO) && jugador.getPorcentajeDefensas() <= 0.60)) {
			LOG.info("El jugador requiere ejercicios de defensa");
			recomendados.addAll(ejercicioIndividualRepository.findByTipoEjercicio(TipoEjercicio.DEFENSA));
		}
		
		//Si el jugador tiene menos porcentaje de bloqueo del mínimo requerido
		if(jugador.getPorcentajeBloqueos() <= 0.50
				|| (jugador.getPosicionPrincipal().equals(Posicion.CENTRAL) && jugador.getPorcentajeBloqueos() <= 0.70)) {
			LOG.info("El jugador requiere ejercicios de bloqueo");
			recomendados.addAll(ejercicioIndividualRepository.findByTipoEjercicio(TipoEjercicio.BLOQUEO));
		}
		
		//Si el jugador tiene menos porcentaje de ataque del mínimo requerido
		if((calcularPorcentajeAtaque(jugador.getPorcentajeRemates(), jugador.getPorcentajeFintas(), jugador.getPorcentajeAtaquesRapidos()) <= 0.30 && jugador.getPosicionPrincipal().equals(Posicion.COLOCADOR))
				|| (calcularPorcentajeAtaque(jugador.getPorcentajeRemates(), jugador.getPorcentajeFintas(), jugador.getPorcentajeAtaquesRapidos()) <= 0.70 && !jugador.getPosicionPrincipal().equals(Posicion.COLOCADOR))
				|| (calcularPorcentajeAtaque(jugador.getPorcentajeRemates(), jugador.getPorcentajeFintas(), jugador.getPorcentajeAtaquesRapidos()) <= 0.70) && jugador.getPosicionPrincipal().equals(Posicion.OPUESTO)) {
			LOG.info("El jugador requiere ejercicios de ataque");
			recomendados.addAll(ejercicioIndividualRepository.findByTipoEjercicio(TipoEjercicio.ATAQUE));
		}
		
		return recomendados;
	}
	
	private double calcularPorcentajeAtaque(double remate, double finta, double ataqueRapido) {		
		return (remate + finta + ataqueRapido)/3.;
	}

}


