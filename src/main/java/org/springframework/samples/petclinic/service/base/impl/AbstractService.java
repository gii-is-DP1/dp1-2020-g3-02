package org.springframework.samples.petclinic.service.base.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.samples.petclinic.service.base.BaseService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class AbstractService<T> implements BaseService<T>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** Repositorio genérico */
	@Autowired
	protected JpaRepository<T, Serializable> genericRepository;

	@Override
	public Optional<T> findById(final Integer id) {
		Optional<T> returnEntity = genericRepository.findById(id);
		return returnEntity;
	}

	@Override
	public List<T> findAll() {
		List<T> returnEntities = genericRepository.findAll();
		return returnEntities;
	}

	@Override
	public T save(T entity) {
		T returnEntity = genericRepository.save(entity);
		return returnEntity;
	}

	@Override
	public List<T> saveAll(Iterable<T> entities) {
		List<T> returnEntity = genericRepository.saveAll(entities);
		return returnEntity;
	}

	@Override
	public void delete(T entity) {
		genericRepository.delete(entity);		
	}

	@Override
	public void deleteById(Integer id) {
		genericRepository.deleteById(id);
	}

	@Override
	public void deleteByIdSiExiste(Integer id) {
		if(genericRepository.existsById(id)) {
			genericRepository.deleteById(id);
		}
	}

	@Override
	public void deleteAll(List<T> entities) {
		genericRepository.deleteAll(entities);
	}

}
