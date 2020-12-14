package org.springframework.samples.petclinic.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.samples.petclinic.enumerate.TipoEjercicio;
import org.springframework.samples.petclinic.model.EjercicioIndividual;
import org.springframework.samples.petclinic.repository.EjercicioIndividualRepository;
import org.springframework.samples.petclinic.service.EjercicioIndividualService;
import org.springframework.samples.petclinic.service.base.impl.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EjercicioIndividualServiceImpl extends AbstractService<EjercicioIndividual> implements EjercicioIndividualService {
	
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

}


