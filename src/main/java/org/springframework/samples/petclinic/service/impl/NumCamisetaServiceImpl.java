package org.springframework.samples.petclinic.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.samples.petclinic.model.NumCamiseta;
import org.springframework.samples.petclinic.repository.NumCamisetaRespository;
import org.springframework.samples.petclinic.service.NumCamisetaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("numCamisetaService")
public class NumCamisetaServiceImpl implements NumCamisetaService{

	@Autowired
	@Qualifier("numCamisetaRepository")
	private NumCamisetaRespository numCamisetaRepository;

	@Override
	@Transactional(readOnly = true)
	public List<NumCamiseta> findAll() {
		return numCamisetaRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<NumCamiseta> findByIdJugador(int id) {
		return numCamisetaRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<NumCamiseta> findByIdEquipo(int id) {
		return numCamisetaRepository.findById(id);
	}

	@Override
	public NumCamiseta saveNumCamiseta(NumCamiseta numCamiseta) {
		return numCamisetaRepository.save(numCamiseta);
	}

}
