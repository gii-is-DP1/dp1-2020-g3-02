package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Authorities;
import org.springframework.samples.petclinic.model.User;



public interface AuthoritiesRepository extends  CrudRepository<Authorities, String>{
	
	public List<String> findAuthorityByUser(User user);
	public List<Authorities> findByUser(User user);
	public List<Authorities> findByUserAndAuthority(User user, String authority);
	
}
