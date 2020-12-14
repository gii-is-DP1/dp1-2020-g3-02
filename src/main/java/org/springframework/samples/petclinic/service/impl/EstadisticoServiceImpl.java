package org.springframework.samples.petclinic.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.samples.petclinic.model.Estadistico;
import org.springframework.samples.petclinic.repository.EstadisticoRepository;
import org.springframework.samples.petclinic.service.EstadisticoService;
import org.springframework.samples.petclinic.service.base.impl.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("estadisticoService")
public class EstadisticoServiceImpl extends AbstractService<Estadistico> implements EstadisticoService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	@Qualifier("estadisticoRepository")
	private EstadisticoRepository estadisticoRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthoritiesService authoritiesService;

	@Override
	@Transactional(readOnly = true)
	public List<Estadistico> findByFirstName(String name) {
		return estadisticoRepository.findByFirstName(name);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Estadistico> findByEmail(String email) {
		return estadisticoRepository.findByEmail(email);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Estadistico> findByFechaNacimientoBetweenOrderByFechaNacimiento(LocalDate firstDate,
			LocalDate secondDate) {
		return estadisticoRepository.findByFechaNacimientoBetweenOrderByFechaNacimiento(firstDate, secondDate);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Estadistico> findByFechaNacimientoAfterOrderByFechaNacimiento(LocalDate date) {
		return estadisticoRepository.findByFechaNacimientoAfterOrderByFechaNacimiento(date);
	}

	@Override
	public Estadistico save(Estadistico estadistico) {
		Estadistico _estadistico = estadisticoRepository.save(estadistico);
		userService.saveUser(estadistico.getUser());
		authoritiesService.saveAuthorities(estadistico.getUser().getUsername(),"estadistico");
		return _estadistico;
	}

	@Override
	public int estadisticoCount() {
		return (int) estadisticoRepository.count();
	}

}
