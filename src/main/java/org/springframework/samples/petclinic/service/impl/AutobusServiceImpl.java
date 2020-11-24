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
	@Transactional(readOnly = true)
	public List<Integer> findByPartido(int partido_id) {
		return autobusRepository.findByPartido(partido_id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Integer> findByJugador(int jugador_id) {
		return autobusRepository.findByJugador(jugador_id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Autobus> findByHoraSalida(String hora_salida) {
		return autobusRepository.findByHoraSalida(hora_salida);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Autobus> findByHoraLlegada(String hora_llegada) {
		return autobusRepository.findByHoraLlegada(hora_llegada);
	}
	
	@Override
	@Transactional
	public Autobus saveAutobus(Autobus autobus) {
		
		Autobus _autobus = autobusRepository.save(autobus);
		
		return _autobus;

	}
	
}
