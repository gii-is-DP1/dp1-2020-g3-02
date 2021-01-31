package org.springframework.samples.petclinic.model.auxiliares;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonalEdit {
	private Integer id;
	private JugadorDTO jugador;
	private String propietario;
}
