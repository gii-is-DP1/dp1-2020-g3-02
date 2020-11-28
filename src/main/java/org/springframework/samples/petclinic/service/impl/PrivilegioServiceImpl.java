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
import org.springframework.samples.petclinic.model.SistemaJuego;
import org.springframework.samples.petclinic.repository.PrivilegioRepository;
import org.springframework.samples.petclinic.repository.SistemaJuegoRepository;
import org.springframework.samples.petclinic.service.PrivilegioService;
import org.springframework.samples.petclinic.service.SistemaJuegoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PrivilegioServiceImpl implements PrivilegioService {
		private static final Log LOG = LogFactory.getLog(SistemaJuegoServiceImpl.class);

		@Autowired
		private PrivilegioRepository privilegioRepository;

	
		@Override
		@Transactional(readOnly = true)
		public List<Privilegio> findAll() {
			return privilegioRepository.findAll();
		}

		@Override
		@Transactional(readOnly = true)
		public Optional<Privilegio> findById(int id) {
			return privilegioRepository.findById(id);
		}

	
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
		@Transactional
		public Privilegio savePrivilegio(Privilegio privilegio) {
			return privilegioRepository.save(privilegio);
		}

}
