package org.springframework.samples.petclinic.service.impl;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.samples.petclinic.enumerate.Actitud;
import org.springframework.samples.petclinic.model.Capitan;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.repository.CapitanRepository;
import org.springframework.samples.petclinic.service.CapitanService;
import org.springframework.samples.petclinic.service.EquipoService;
import org.springframework.samples.petclinic.service.base.impl.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("capitanService")
public class CapitanServiceImpl extends AbstractService<Capitan> implements CapitanService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Log LOG = LogFactory.getLog(CapitanServiceImpl.class);

	@Autowired
	@Qualifier("capitanRepository")
	private CapitanRepository capitanRepository;
	
	@Autowired
	private EquipoService equipoService;
	
	@Autowired
	private CapitanService capitanService;
	
	@Override
	@Transactional(readOnly = true)
	public List<Capitan> findByActitud(Actitud actitud) {
		return capitanRepository.findByActitud(actitud);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Capitan> findByNtiemposmuertos(Integer ntiemposmuertos) {
		return capitanRepository.findByNtiemposmuertos(ntiemposmuertos);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Capitan> findByEquipo(int equipo_id) {
		return capitanRepository.findByEquipo(equipo_id);
	}

	@Override
	public Capitan findByJugador(Jugador jugador) {
		
		return capitanRepository.findByJugador(jugador);
	}
	
	@Override
	public Capitan saveCapitan(Capitan capitan) {
		LOG.info("CAPITAN INSERTADO: " + capitan.getJugador().getFirstName() + " " + capitan.getJugador().getLastName());
		
		Capitan c = capitanRepository.save(capitan);
		
		return c;
	}
}
