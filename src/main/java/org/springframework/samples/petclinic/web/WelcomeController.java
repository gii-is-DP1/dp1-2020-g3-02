package org.springframework.samples.petclinic.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.samples.petclinic.model.Person;
import org.springframework.samples.petclinic.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WelcomeController {
	
	
	  @GetMapping({"/","/welcome"})
	  public String welcome(Map<String, Object> model) {	    

		  List<Person> persons = new ArrayList<Person>();
		  Person person = new Person();
		  person.setFirstName("Diego Manuel");
		  person.setLastName("Gil Losa");
		  persons.add(person);
		  model.put("persons", person);
		  
		  person.setFirstName("Gonzalo");
		  person.setLastName("Lallena Alva");
		  persons.add(person);
		  model.put("persons", person);
		  
		  person.setFirstName("Laura");
		  person.setLastName("Castillo Ortiz");
		  persons.add(person);
		  model.put("persons", person);
		  
		  person.setFirstName("Blanca del Rosario");
		  person.setLastName("Mauri Robles");
		  persons.add(person);
		  model.put("persons", person);
		  
		  person.setFirstName("Javier");
		  person.setLastName("Gutierrez Falcon");
		  persons.add(person);
		  model.put("persons", person);
		  
		  person.setFirstName("Benjamín");
		  person.setLastName("Crespo Alcaide");
		  persons.add(person);
		  model.put("persons", person);
		  
		  model.put("title", "Volleyball");
		  model.put("group","G3-02");
		  
	    return "welcome";
	  }
}
