package org.springframework.samples.petclinic.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Entrenador;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.repository.EntrenadorRepository;
import org.springframework.samples.petclinic.service.EntrenadorService;
import org.springframework.samples.petclinic.service.base.impl.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("entrenadorService")
public class EntrenadorServiceImpl extends AbstractService<Entrenador> implements EntrenadorService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	@Qualifier("entrenadorRepository")
	private EntrenadorRepository entrenadorRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthoritiesService authoritiesService;

	@Override
	public List<Entrenador> findByFirstName(String name) throws DataAccessException {
		return entrenadorRepository.findByFirstName(name);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Entrenador> findByEmail(String email) throws DataAccessException {
		return entrenadorRepository.findByEmail(email);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Entrenador> findByFechaNacimientoBetweenOrderByFechaNacimiento(LocalDate firstDate,
			LocalDate secondDate) {
		return entrenadorRepository.findByFechaNacimientoBetweenOrderByFechaNacimiento(firstDate, secondDate);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Entrenador> findByFechaNacimientoAfterOrderByFechaNacimiento(LocalDate date) throws DataAccessException {
		return entrenadorRepository.findByFechaNacimientoAfterOrderByFechaNacimiento(date);
	}

	@Override
	@Transactional
	public Entrenador save(Entrenador entrenador) throws DataAccessException {
		Entrenador _entrenador = entrenadorRepository.save(entrenador);
		//LOG.info(_entrenador.toString());
		userService.saveUser(entrenador.getUser());
		authoritiesService.saveAuthorities(entrenador.getUser().getUsername(),"entrenador");
		return _entrenador;
	}

	@Override
	public int entrenadorCount() {
		return (int) entrenadorRepository.count();
	}

	@Override
	public Entrenador findByUser(User user) {
		return entrenadorRepository.findByUser(user);
	}

}
