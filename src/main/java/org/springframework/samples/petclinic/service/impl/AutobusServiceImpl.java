package org.springframework.samples.petclinic.service.impl;

import org.springframework.samples.petclinic.model.Autobus;
import org.springframework.samples.petclinic.service.AutobusService;
import org.springframework.samples.petclinic.service.base.impl.AbstractService;
import org.springframework.stereotype.Service;

@Service("autobusService")
public class AutobusServiceImpl extends AbstractService<Autobus> implements AutobusService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
