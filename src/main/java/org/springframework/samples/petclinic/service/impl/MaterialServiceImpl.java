package org.springframework.samples.petclinic.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.samples.petclinic.enumerate.EstadoMaterial;
import org.springframework.samples.petclinic.enumerate.TipoMaterial;
import org.springframework.samples.petclinic.model.LineaMaterial;
import org.springframework.samples.petclinic.model.Material;
import org.springframework.samples.petclinic.repository.MaterialRepository;
import org.springframework.samples.petclinic.service.LineaMaterialService;
import org.springframework.samples.petclinic.service.MaterialService;
import org.springframework.samples.petclinic.service.base.impl.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("materialService")
public class MaterialServiceImpl extends AbstractService<Material> implements MaterialService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Log LOG = LogFactory.getLog(MaterialServiceImpl.class);

	@Autowired
	@Qualifier("materialRepository")
	private MaterialRepository materialRepository;

	@Autowired
	private MaterialService materialService;

	@Autowired
	private LineaMaterialService lineaMaterialService;

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
	public  int porcentajeUso(TipoMaterial material){
		//Optional<Material> materialito = materialRepository.findById(material);
		//TipoMaterial tipo = materialito.get().getTipo();
		List<Material> materialesDeUnTipo= new ArrayList<>();
		materialesDeUnTipo.addAll(materialService.findByTipo(material));
		List<LineaMaterial> lineasdndeseusaelmaterial= new ArrayList<>();
		int stock=0; //se me guarda el stock total de ese tipo de material en caso de cono alto se guarda un 9
		int finl=0;
		int max=0;
		int usados=0;
		LocalTime hora=null;
		
		
		for(Material en:materialesDeUnTipo) {
			if(!(en.getEstado()==EstadoMaterial.DAÑADO) &&!(en.getEstado()==EstadoMaterial.INSERVIBLE)) {
				stock+=en.getStock();
			}


			lineasdndeseusaelmaterial.addAll(lineaMaterialService.findByMaterial(en.getId()));

			if(en.getEstado()==EstadoMaterial.DAÑADO ||en.getEstado()==EstadoMaterial.INSERVIBLE || lineasdndeseusaelmaterial.size()==0  ) {
				finl+=0;
			}else {

				for(LineaMaterial linea: lineasdndeseusaelmaterial) {

					usados+=linea.getCantidad();
					if(!(LocalTime.parse(linea.getEntrenamiento().getHora())==hora)) {
						hora=LocalTime.parse(linea.getEntrenamiento().getHora());
						max+=stock;
					}

				}
				finl=(usados*100/max);

			}
			
		}
		if(finl>100) {
			return 100;
		}else {
			return finl;
		}
	}


	@Override
	@Transactional
	public Material saveMaterial(Material material) {
		LOG.info("NÚMERO DE ELEMENTOS INSERTADOS: "+material.getStock());


		Material materiall=materialRepository.save(material);
		materialService.saveMaterial(material);



		LOG.info(materiall.toString());
		return materiall;


	}


}
