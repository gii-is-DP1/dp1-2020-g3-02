package org.springframework.samples.petclinic.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.enumerate.TipoPrivilegio;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.Privilegio;
import org.springframework.samples.petclinic.repository.PrivilegioRepository;
import org.springframework.samples.petclinic.service.EquipoService;
import org.springframework.samples.petclinic.service.PrivilegioService;
import org.springframework.samples.petclinic.service.base.impl.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PrivilegioServiceImpl extends AbstractService<Privilegio> implements PrivilegioService {
		
		private static final long serialVersionUID = 1L;

		private static final Log LOG = LogFactory.getLog(SistemaJuegoServiceImpl.class);

		@Autowired
		private PrivilegioRepository privilegioRepository;
		
		@Autowired
		private EquipoService equipoService;
		
		@Autowired
		private PrivilegioService privilegioService;

	
		@Override
		@Transactional(readOnly = true)
		public List<Privilegio> findByDescripcion(String descripcion) {
			return privilegioRepository.findByDescripcion(descripcion);
		}

		@Override
		@Transactional(readOnly = true)
		public List<Privilegio> findByTipoPrivilegio(TipoPrivilegio tipoPrivilegio) {
			return privilegioRepository.findByTipoPrivilegio(tipoPrivilegio);
		}
		

		@Override
		@Transactional(readOnly = true)
		public List<Privilegio> findByJugador(Jugador jugador) {
			return privilegioRepository.findByJugador(jugador);
		}

		@Override
		@Transactional(readOnly = true)
		public List<Privilegio> findByEquipo(Equipo equipo) {
			return privilegioRepository.findByEquipo(equipo);
		}
		
		@Override
		public Privilegio updatePrivilegio(Privilegio privilegio) {
			LOG.info("PRIVILEGIO INSERTADO: "+privilegio.getTipoPrivilegio());
			LOG.info("JUGADOR DEL PRIVILEGIO INSERTADO: "+privilegio.getJugador());
			
			privilegio.setTipoPrivilegio(privilegio.getTipoPrivilegio());
			privilegio.setDescripcion(privilegio.getDescripcion());
			privilegio.setEquipo(privilegio.getEquipo());
			privilegio.setJugador(privilegio.getJugador());
			
			Privilegio priv = privilegioRepository.save(privilegio);
			
			return priv;
		}

		@Override
		public void deleteAllInEquipo(Integer equipo_id) {
			Optional<Equipo> equipo = equipoService.findById(equipo_id);
			List<Privilegio> privilegios =privilegioRepository.findByEquipo(equipo.get());
			for(int i=0;i<privilegios.size();i++) {
				privilegioService.deleteById(privilegios.get(i).getId());
			}
			
		}

}
