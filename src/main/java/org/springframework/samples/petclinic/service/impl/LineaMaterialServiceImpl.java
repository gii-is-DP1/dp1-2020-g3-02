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
import org.springframework.samples.petclinic.repository.EntrenamientoRepository;
import org.springframework.samples.petclinic.repository.LineaMaterialRepository;
import org.springframework.samples.petclinic.repository.MaterialRepository;
import org.springframework.samples.petclinic.service.LineaMaterialService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("lineaMaterialService")
public class LineaMaterialServiceImpl implements LineaMaterialService {
	private static final Log LOG = LogFactory.getLog(LineaMaterialServiceImpl.class);
	
	@Autowired
	@Qualifier("lineaMaterialRepository")
	private LineaMaterialRepository lineaMaterialRepository;
	
	@Autowired
	private MaterialRepository materialRepository;
	
	
	@Autowired
	private EntrenamientoRepository entrenamientoRepository;

	@Override
	@Transactional(readOnly = true)
	public List<LineaMaterial> findAll() {
		return lineaMaterialRepository.findAll();
	}



	@Override
	@Transactional(readOnly = true)
	public Optional<LineaMaterial> findById(int id) {
		return lineaMaterialRepository.findById(id);
	}


	@Override
	@Transactional(readOnly = true)
	public List<LineaMaterial> findByCantidad(int cantidad)  {
		return lineaMaterialRepository.findByCantidad(cantidad);
	}


	@Override
	@Transactional(readOnly = true)
	public List<LineaMaterial> findByMaterial(int material_id) {
		Optional<Material> material= materialRepository.findById(material_id);
		return lineaMaterialRepository.findByMaterial(material.get());
	}

	@Override
	@Transactional(readOnly = true)
	public List<LineaMaterial> findByEntrenamiento(int entrenamiento_id) {
		Optional<Entrenamiento> entrenamiento= entrenamientoRepository.findById(entrenamiento_id);
		return lineaMaterialRepository.findByEntrenamiento(entrenamiento.get());
	}


	@Override
	@Transactional
	public LineaMaterial saveLineaMaterial(LineaMaterial lineaMaterial) {
		return lineaMaterialRepository.save(lineaMaterial);
	}


}
