package org.springframework.samples.petclinic.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.samples.petclinic.constant.ViewConstant;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.model.padres.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class WelcomeController {
	
	
	  @GetMapping("")
	  public String welcome(Map<String, Object> model) {	    

		  List<Person> persons = new ArrayList<Person>();
		  Person person = new Person();
		  person.setFirstName("Diego Manuel");
		  person.setLastName("Gil Losa");
		  persons.add(person);
		  
		  Person person1 = new Person();
		  person1.setFirstName("Gonzalo");
		  person1.setLastName("Lallena Alva");
		  persons.add(person1);
		 
		  Person person2 = new Person();
		  person2.setFirstName("Laura");
		  person2.setLastName("Castillo Ortiz");
		  persons.add(person2);
		  
		  Person person3 = new Person();
		  person3.setFirstName("Blanca del Rosario");
		  person3.setLastName("Mauri Robles");
		  persons.add(person3);
		  
		  Person person4 = new Person();
		  person4.setFirstName("Javier");
		  person4.setLastName("Gutierrez Falcon");
		  persons.add(person4);
		  
		  Person person5 = new Person();
		  person5.setFirstName("Benjamín");
		  person5.setLastName("Crespo Alcaide");
		  persons.add(person5);
		  
		  model.put("persons", persons);
		  
		  model.put("title", "Volleyball");
		  model.put("group","G3-02");
		  
	    return "start";
	  }
	  
	  @GetMapping("/login")
	  public String login() {
		  return ViewConstant.VIEW_LOGIN;
	  }
	  
	  @GetMapping("/home")
	  public String home() {
		  return ViewConstant.VIEW_HOME;
	  }
	  
	  @GetMapping("/navbar")
		public String navbar() {
			return ViewConstant.VIEW_NAVBAR;
		}
}
