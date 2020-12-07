package org.springframework.samples.petclinic.model;

import java.util.Set;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.springframework.samples.petclinic.model.padres.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@Entity
@NoArgsConstructor
@Table(name = "autobus")
public class Autobus extends BaseEntity {
		
	

}