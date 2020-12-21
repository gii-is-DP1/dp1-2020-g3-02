package org.springframework.samples.petclinic.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.Material;
import org.springframework.samples.petclinic.model.Personales;
import org.springframework.samples.petclinic.repository.JugadorRepository;
import org.springframework.samples.petclinic.repository.PersonalesRepository;
import org.springframework.samples.petclinic.service.MaterialService;
import org.springframework.samples.petclinic.service.PersonalesService;
import org.springframework.samples.petclinic.service.base.impl.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonalesServiceImpl extends AbstractService<Personales>  implements PersonalesService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Log LOG = LogFactory.getLog(PersonalesServiceImpl.class);

	@Autowired
	@Qualifier("personalesRepository")
	private PersonalesRepository personalesRepository;
	
	@Autowired
	private JugadorRepository jugadorRepository;
	
	@Autowired
	private PersonalesService personalesService;

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
	@Transactional
	public Personales savePersonal(Personales personal) {


		Personales personalito=personalesRepository.save(personal);
		personalesService.savePersonal(personal);


		
		LOG.info(personalito.toString());
		return personalito;


	}

}
