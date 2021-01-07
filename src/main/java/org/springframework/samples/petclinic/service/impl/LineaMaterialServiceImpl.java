package org.springframework.samples.petclinic.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.samples.petclinic.model.Entrenamiento;
import org.springframework.samples.petclinic.model.LineaMaterial;
import org.springframework.samples.petclinic.model.Material;
import org.springframework.samples.petclinic.repository.LineaMaterialRepository;
import org.springframework.samples.petclinic.service.EntrenamientoService;
import org.springframework.samples.petclinic.service.LineaMaterialService;
import org.springframework.samples.petclinic.service.MaterialService;
import org.springframework.samples.petclinic.service.base.impl.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("lineaMaterialService")
public class LineaMaterialServiceImpl extends AbstractService<LineaMaterial> implements LineaMaterialService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Log LOG = LogFactory.getLog(LineaMaterialServiceImpl.class);
	
	@Autowired
	@Qualifier("lineaMaterialRepository")
	private LineaMaterialRepository lineaMaterialRepository;
	
	@Autowired
	private MaterialService materialService;
	
	
	@Autowired
	private EntrenamientoService entrenamientoService;

	@Override
	@Transactional(readOnly = true)
	public List<LineaMaterial> findByCantidad(int cantidad)  {
		return lineaMaterialRepository.findByCantidad(cantidad);
	}


	@Override
	@Transactional(readOnly = true)
	public List<LineaMaterial> findByMaterial(int material_id) {
		Optional<Material> material= materialService.findById(material_id);
		return lineaMaterialRepository.findByMaterial(material.get());
	}

	@Override
	@Transactional(readOnly = true)
	public List<LineaMaterial> findByEntrenamiento(int entrenamiento_id) {
		Optional<Entrenamiento> entrenamiento= entrenamientoService.findById(entrenamiento_id);
		return lineaMaterialRepository.findByEntrenamiento(entrenamiento.get());
	}


	@Override
	public void deleteAllInEntrenamiento(Integer entrenamiento_id) {
		Optional<Entrenamiento> entrenamiento = entrenamientoService.findById(entrenamiento_id);
		List<LineaMaterial> lineasMaterial =lineaMaterialRepository.findByEntrenamiento(entrenamiento.get());
		lineaMaterialRepository.deleteAll(lineasMaterial);
		
	}
}
