package org.springframework.samples.petclinic.model.ediciones;

import java.util.List;
import java.util.Set;

import org.springframework.samples.petclinic.enumerate.TipoPrivilegio;
import org.springframework.samples.petclinic.model.Privilegio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrivilegioEdit {
	
	private int id;
	private List<TipoPrivilegio> privilegios;
	private String descripcion;
	
}
