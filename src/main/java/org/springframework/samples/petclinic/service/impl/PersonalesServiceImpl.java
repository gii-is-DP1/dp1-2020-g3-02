package org.springframework.samples.petclinic.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.Personales;
import org.springframework.samples.petclinic.repository.PersonalesRepository;
import org.springframework.samples.petclinic.service.JugadorService;
import org.springframework.samples.petclinic.service.PersonalesService;
import org.springframework.samples.petclinic.service.base.impl.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("personalesService")
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
	private JugadorService jugadorService;
	
	@Autowired
	private PersonalesService personalesService;

	@Override
	public List<Personales> findByPropietario(String propietario)  {
		return personalesRepository.findByPropietario(propietario);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Personales> findByJugador(int jugador_id) {
		Optional<Jugador> jugador= jugadorService.findById(jugador_id);
		return personalesRepository.findByJugador(jugador.get());
	}
	
}
