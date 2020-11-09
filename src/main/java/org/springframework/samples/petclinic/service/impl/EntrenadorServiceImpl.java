package org.springframework.samples.petclinic.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Entrenador;
import org.springframework.samples.petclinic.repository.EntrenadorRepository;
import org.springframework.samples.petclinic.service.EntrenadorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("entrenadorService")
public class EntrenadorServiceImpl implements EntrenadorService{

	@Autowired
	@Qualifier("entrenadorRepository")
	private EntrenadorRepository entrenadorRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	@Override
	@Transactional(readOnly = true)
	public List<Entrenador> findAll() {
		// TODO Auto-generated method stub
		return entrenadorRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Entrenador> findById(int id) {
		// TODO Auto-generated method stub
		return entrenadorRepository.findById(id);
	}

	@Override
	public List<Entrenador> findByFirstName(String name) throws DataAccessException {
		// TODO Auto-generated method stub
		return entrenadorRepository.findByFirstName(name);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Entrenador> findByEmail(String email) throws DataAccessException {
		// TODO Auto-generated method stub
		return entrenadorRepository.findByEmail(email);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Entrenador> findByFechaNacimientoBetweenOrderByFechaNacimiento(LocalDate firstDate,
			LocalDate secondDate) {
		// TODO Auto-generated method stub
		return entrenadorRepository.findByFechaNacimientoBetweenOrderByFechaNacimiento(firstDate, secondDate);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Entrenador> findByFechaNacimientoAfterOrderByFechaNacimiento(LocalDate date) throws DataAccessException {
		// TODO Auto-generated method stub
		return entrenadorRepository.findByFechaNacimientoAfterOrderByFechaNacimiento(date);
	}

	@Override
	@Transactional
	public Entrenador saveEntrenador(Entrenador entrenador) throws DataAccessException {
		// TODO Auto-generated method stub
		Entrenador _entrenador = entrenadorRepository.save(entrenador);
		//LOG.info(_entrenador.toString());
		userService.saveUser(entrenador.getUser());
		authoritiesService.saveAuthorities(entrenador.getUser().getUsername(),"entrenador");
		return _entrenador;
	}

	@Override
	public int entrenadorCount() {
		// TODO Auto-generated method stub
		return (int) entrenadorRepository.count();
	}

}