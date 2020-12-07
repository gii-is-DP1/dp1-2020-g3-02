package org.springframework.samples.petclinic.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.samples.petclinic.model.Autobus;
import org.springframework.samples.petclinic.repository.AutobusRepository;
import org.springframework.samples.petclinic.service.AutobusService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("autobusService")
public class AutobusServiceImpl implements AutobusService {

	private static final Log LOG = LogFactory.getLog(AutobusServiceImpl.class);
	
	@Autowired
	@Qualifier("autobusRepository")
	private AutobusRepository autobusRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Autobus> findAll() {
		return autobusRepository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Autobus> findById(int id) {
		return autobusRepository.findById(id);
	}
	
	@Override
	@Transactional
	public Autobus saveAutobus(Autobus autobus) {
		
		Autobus _autobus = autobusRepository.save(autobus);
		
		return _autobus;

	}
	
}
