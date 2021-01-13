package org.springframework.samples.petclinic.web.base;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.samples.petclinic.component.EntrenadorValidator;
import org.springframework.samples.petclinic.component.PartidoValidator;
import org.springframework.samples.petclinic.converter.DataPosicionConverter;
import org.springframework.samples.petclinic.converter.EstadisticasConverter;
import org.springframework.samples.petclinic.converter.JugadorPartidoStatsConverter;
import org.springframework.samples.petclinic.converter.PartidoConverter;
import org.springframework.samples.petclinic.converter.ViajeConverter;
import org.springframework.samples.petclinic.service.EntrenadorService;
import org.springframework.samples.petclinic.service.EquipoService;
import org.springframework.samples.petclinic.service.EstadisticaPersonalPartidoService;
import org.springframework.samples.petclinic.service.JugadorService;
import org.springframework.samples.petclinic.service.PartidoService;
import org.springframework.samples.petclinic.service.ViajeService;
import org.springframework.samples.petclinic.service.impl.UserService;

public class BaseMockControllerTest {
	
	@MockBean
	protected PartidoService partidoService;
	
	@MockBean
	protected EquipoService equipoService;
	
	@MockBean
	protected PartidoConverter partidoConverter;
	
	@MockBean
	protected JugadorPartidoStatsConverter jugadorPartidoStatsConverter;
	
	@MockBean
	protected EstadisticaPersonalPartidoService estadisticaPersonalPartidoService;
	
	@MockBean
	protected JugadorService jugadorService;
	
	@MockBean
	protected EntrenadorService entrenadorService;
	
	@MockBean
	protected EntrenadorValidator entrenadorValidator;
	
	@MockBean
	protected UserService userService;
	
	@MockBean
	protected PartidoValidator partidoValidator;
	
	@MockBean
	protected DataPosicionConverter dataPosicionConverter;
	
	@MockBean
	protected EstadisticasConverter estadisticasConverter;
	
	@MockBean
	protected ViajeService viajeService;
	
	@MockBean
	protected ViajeConverter viajeConverter;

}
