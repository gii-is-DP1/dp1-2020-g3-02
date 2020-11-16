package org.springframework.samples.petclinic.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.samples.petclinic.enumerate.Actitud;
import org.springframework.samples.petclinic.model.Capitan;
import org.springframework.samples.petclinic.repository.CapitanRepository;
import org.springframework.samples.petclinic.service.CapitanService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("capitanService")
public class CapitanServiceImpl implements CapitanService {
	
	private static final Log LOG = LogFactory.getLog(CapitanServiceImpl.class);

	@Autowired
	@Qualifier("capitanRepository")
	private CapitanRepository capitanRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Capitan> findAll(){
		return capitanRepository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Capitan> findByActitud(Actitud actitud) {
		return capitanRepository.findByActitud(actitud);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Capitan> findByNtiemposmuertos(Integer ntiemposmuertos) {
		return capitanRepository.findByNtiemposmuertos(ntiemposmuertos);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Capitan> findByEquipo(int equipo_id) {
		return capitanRepository.findByEquipo(equipo_id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Capitan> findById(int id) {
		return capitanRepository.findById(id);
	}
	
	@Override
	@Transactional
	public Capitan saveCapitan(Capitan capitan) {
		
		Capitan c = capitanRepository.save(capitan);
		
		return c;

	}
	
	@Override
	@Transactional
	public void deleteCapitan(int id) {
		Optional<Capitan> vacio = Optional.empty();
		Optional<Capitan> c = findById(id);

		if(c != vacio) {
			capitanRepository.delete(c.get());
		}

	}
}
