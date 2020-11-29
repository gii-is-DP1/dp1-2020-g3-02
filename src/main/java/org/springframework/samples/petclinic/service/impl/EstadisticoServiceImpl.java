package org.springframework.samples.petclinic.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.samples.petclinic.model.Entrenador;
import org.springframework.samples.petclinic.model.Estadistico;
import org.springframework.samples.petclinic.repository.EstadisticoRepository;
import org.springframework.samples.petclinic.service.EstadisticoService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("estadisticoService")
public class EstadisticoServiceImpl implements EstadisticoService{

	@Autowired
	@Qualifier("estadisticoRepository")
	private EstadisticoRepository estadisticoRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthoritiesService authoritiesService; 
	
	@Override
	@Transactional(readOnly = true)
	public List<Estadistico> findAll() {
		// TODO Auto-generated method stub
		return estadisticoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Estadistico> findById(int id) {
		// TODO Auto-generated method stub
		return estadisticoRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Estadistico> findByFirstName(String name) {
		// TODO Auto-generated method stub
		return estadisticoRepository.findByFirstName(name);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Estadistico> findByEmail(String email) {
		// TODO Auto-generated method stub
		return estadisticoRepository.findByEmail(email);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Estadistico> findByFechaNacimientoBetweenOrderByFechaNacimiento(LocalDate firstDate,
			LocalDate secondDate) {
		// TODO Auto-generated method stub
		return estadisticoRepository.findByFechaNacimientoBetweenOrderByFechaNacimiento(firstDate, secondDate);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Estadistico> findByFechaNacimientoAfterOrderByFechaNacimiento(LocalDate date) {
		// TODO Auto-generated method stub
		return estadisticoRepository.findByFechaNacimientoAfterOrderByFechaNacimiento(date);
	}

	@Override
	public Estadistico saveEstadistico(Estadistico estadistico) {
		Estadistico _estadistico = estadisticoRepository.save(estadistico);
		//LOG.info(_entrenador.toString());
		userService.saveUser(estadistico.getUser());
		authoritiesService.saveAuthorities(estadistico.getUser().getUsername(),"estadistico");
		return _estadistico;
	}

	@Override
	public int estadisticoCount() {
		// TODO Auto-generated method stub
		return (int) estadisticoRepository.count();
	}

}
