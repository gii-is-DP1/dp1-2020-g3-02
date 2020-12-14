package org.springframework.samples.petclinic.service.base;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseService<T> extends Serializable{
	
	public abstract Optional<T> findById(Integer id);
	
	public abstract List<T> findAll();
	
	public abstract T save(T entity);
	
	public abstract List<T> saveAll(Iterable<T> entities);
	
	public abstract void delete(T entity);
	
	public abstract void deleteById(Integer id);
	
	public abstract void deleteByIdSiExiste(Integer id);
	
	public abstract void deleteAll(List<T> entities);

}
