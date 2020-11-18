package org.springframework.samples.petclinic.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.LineaMaterial;
import org.springframework.samples.petclinic.repository.LineaMaterialRepository;
import org.springframework.samples.petclinic.service.LineaMaterialService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class LineaMaterialServiceImpl implements LineaMaterialService {
	private static final Log LOG = LogFactory.getLog(SistemaJuegoServiceImpl.class);
	
	@Autowired
	private LineaMaterialRepository lineaMaterialRepository;


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
	public Optional<LineaMaterial> findByMaterial(int material_id) {
		return lineaMaterialRepository.findByMaterial(material_id);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<LineaMaterial> findByEntrenamiento(int entrenamiento_id) {
		return lineaMaterialRepository.findByEntrenamiento(entrenamiento_id);
	}


	@Override
	@Transactional
	public LineaMaterial saveLineaMaterial(LineaMaterial lineaMaterial) {
		return lineaMaterialRepository.save(lineaMaterial);
	}


}
