package org.springframework.samples.petclinic.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.samples.petclinic.enumerate.TipoMaterial;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.LineaMaterial;
import org.springframework.samples.petclinic.model.Material;
import org.springframework.samples.petclinic.repository.LineaMaterialRepository;
import org.springframework.samples.petclinic.repository.MaterialRepository;
import org.springframework.samples.petclinic.service.MaterialService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("materialService")
public class MaterialServiceImpl implements MaterialService {

	private static final Log LOG = LogFactory.getLog(MaterialServiceImpl.class);
	
	@Autowired
	@Qualifier("materialRepository")
	private MaterialRepository materialRepository;
	
	@Autowired
	private MaterialService materialService;
	
	@Autowired
	private LineaMaterialRepository lineaMaterialRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Material> findAll() {
		return materialRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Material> findById(int id) {
		return materialRepository.findById(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Material> findByTipo(TipoMaterial tipo) {
		return materialRepository.findByTipo(tipo);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Material> findByDescripcion(String descripcion) {
		return materialRepository.findByDescripcion(descripcion);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Material> findByStock(int stock) {
		return materialRepository.findByStock(stock);
	}
	@Override
	public Material updateMaterial(Material material) {
		LOG.info("NÚMERO DE ELEMENTOS INSERTADOS: "+material.getStock());
		
		
			
		materialService.saveMaterial(material);
		
		
		Material materiall=materialRepository.save(material);
		
		return materiall;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Material> findByLineaMaterial(int linea_material_id){
		Optional<LineaMaterial> linea_material= lineaMaterialRepository.findById(linea_material_id);
		return materialRepository.findByLineaMaterial(linea_material.get());
	}
	
	@Override
	@Transactional(readOnly = true)
	public Material saveMaterial(Material material) {
		return materialRepository.save(material);
	}
}
