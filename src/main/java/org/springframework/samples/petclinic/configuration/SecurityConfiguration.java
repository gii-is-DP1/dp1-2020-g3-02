package org.springframework.samples.petclinic.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author japarejo
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception  {
		http.sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
		http.authorizeRequests()
				.antMatchers("/resources/**","/webjars/**","/h2-console/**").permitAll()
				//.antMatchers("/**").permitAll()
				.antMatchers("/home").hasAnyAuthority("jugador","entrenador","estadistico")
				.antMatchers("/navbar").hasAnyAuthority("jugador","entrenador","estadistico")
				.antMatchers(HttpMethod.GET, "/","/oups").permitAll()
				.antMatchers("/users/new").permitAll()
				.antMatchers("/jugadores/jugadorform").permitAll()
				.antMatchers("/jugadores/addjugador").permitAll()
				.antMatchers("/jugadores/**").hasAnyAuthority("jugador","entrenador")
				.antMatchers("/ejercicios/**").hasAnyAuthority("jugador","entrenador")
				.antMatchers("/entrenadores/entrenadorform").permitAll()
				.antMatchers("/entrenadores/addentrenador").permitAll()
				.antMatchers("/entrenadores/**").permitAll()
				.antMatchers("/estadisticos/estadisticoform").permitAll()
				.antMatchers("/estadisticos/addestadistico").permitAll()
				.antMatchers("/estadisticos/**").permitAll()
				.antMatchers("/users/**").permitAll()
				.antMatchers("/estadisticas/**").hasAnyAuthority("estadistico","entrenador")
				.antMatchers("/pruebas/**").hasAnyAuthority("jugador","entrenador")
				.antMatchers("/cuerpotecnico/**").hasAnyAuthority("jugador","entrenador","estadistico")
				.antMatchers("/equipos/**").hasAnyAuthority("jugador","entrenador")
				.antMatchers("/equipos/navbar").hasAnyAuthority("jugador","entrenador")
				.antMatchers("/partidos/showpartidos").hasAnyAuthority("jugador","entrenador","estadistico")
				.antMatchers("/partidos/findPartidos").hasAnyAuthority("jugador","entrenador","estadistico")
				.antMatchers("/partidos/navbar").hasAnyAuthority("jugador","entrenador","estadistico")
				.antMatchers("/partidos/**").hasAnyAuthority("jugador","entrenador")
				.antMatchers("/entrenamientos/showentrenamientos").hasAnyAuthority("jugador","entrenador","estadistico")
				.antMatchers("/entrenamientos/findEntrenamientos").hasAnyAuthority("jugador","entrenador","estadistico")
				.antMatchers("/entrenamientos/navbar").hasAnyAuthority("jugador","entrenador","estadistico")
				.antMatchers("/entrenamientos/**").hasAnyAuthority("jugador","entrenador")
				.antMatchers("/materiales/**").hasAnyAuthority("jugador","entrenador")
				.antMatchers("/material/materialesform").permitAll()
				.antMatchers("/materiales/navbar").hasAnyAuthority("jugador","entrenador","estadistico")
				.antMatchers("/personales/showvehiculos").hasAnyAuthority("jugador","entrenador")
				.antMatchers("/personales/postvehiculo").hasAuthority("jugador")
				.antMatchers("/personales/findeditvehiculo/**").hasAuthority("jugador")
				.antMatchers("/personales/eliminarVehiculo/**").hasAnyAuthority("jugador")
				.antMatchers("/personales/navbar").hasAnyAuthority("jugador","entrenador","estadistico")
				
				.antMatchers("/admin/**").hasAnyAuthority("admin")
				.antMatchers("/owners/**").hasAnyAuthority("owner","admin")				
				.antMatchers("/vets/**").authenticated()
				.anyRequest().denyAll()
				.and()
				 	.formLogin().permitAll()
				 	.loginPage("/login")
				 		.defaultSuccessUrl("/home")
				 	
				 	//.failureUrl("/login-error")
				.and()
					.logout()
						.logoutSuccessUrl("/");
                // Configuración para que funcione la consola de administración 
                // de la BD H2 (deshabilitar las cabeceras de protección contra
                // ataques de tipo csrf y habilitar los framesets si su contenido
                // se sirve desde esta misma página.
                http.csrf().ignoringAntMatchers("/**");
                http.headers().frameOptions().sameOrigin();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
	      .dataSource(dataSource)
	      .usersByUsernameQuery(
	       "select username,password,enabled "
	        + "from users "
	        + "where username = ?")
	      .authoritiesByUsernameQuery(
	       "select username, authority "
	        + "from authorities "
	        + "where username = ?")	      	      
	      .passwordEncoder(passwordEncoder());	
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {	    
		PasswordEncoder encoder =  NoOpPasswordEncoder.getInstance();
	    return encoder;
	}
	
}


