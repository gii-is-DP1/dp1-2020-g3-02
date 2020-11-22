package org.springframework.samples.petclinic.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.LineaMaterial;
import org.springframework.samples.petclinic.model.Material;
import org.springframework.samples.petclinic.model.Personales;
import org.springframework.samples.petclinic.repository.JugadorRepository;
import org.springframework.samples.petclinic.repository.MaterialRepository;
import org.springframework.samples.petclinic.repository.PersonalesRepository;
import org.springframework.samples.petclinic.service.PersonalesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonalesServiceImpl  implements PersonalesService {

	private static final Log LOG = LogFactory.getLog(PersonalesServiceImpl.class);

	@Autowired
	@Qualifier("personalesRepository")
	private PersonalesRepository personalesRepository;
	
	@Autowired
	private JugadorRepository jugadorRepository;


	@Override
	@Transactional(readOnly = true)
	public List<Personales> findAll() {
		return personalesRepository.findAll();
	}



	@Override
	@Transactional(readOnly = true)
	public Optional<Personales> findById(int id) {
		return personalesRepository.findById(id);
	}


	@Override
	@Transactional(readOnly = true)
	public List<Personales> findByPropietario(String propietario)  {
		return personalesRepository.findByPropietario(propietario);
	}


	@Override
	@Transactional(readOnly = true)
	public List<Personales> findByJugador(int jugador_id) {
		Optional<Jugador> jugador= jugadorRepository.findById(jugador_id);
		return personalesRepository.findByJugador(jugador.get());
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Personales> findByPartido(int partido_id) {
		return personalesRepository.findByPartido(partido_id);
	}


	@Override
	@Transactional
	public Personales savePersonales(Personales personales) {
		return personalesRepository.save(personales);
	}

}
