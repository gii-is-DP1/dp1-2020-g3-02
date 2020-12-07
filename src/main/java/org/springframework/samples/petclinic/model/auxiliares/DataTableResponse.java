package org.springframework.samples.petclinic.model.auxiliares;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataTableResponse<T> {
	
	private List<T> data;

}
