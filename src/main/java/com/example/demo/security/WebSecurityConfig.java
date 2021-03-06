package com.example.demo.security;

import java.util.Arrays;

import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.demo.services.impl.UserServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Lazy
	@Autowired
	private UserServiceImpl jwtUserDetailsService;

	@Autowired
	private TokenUtils tokenUtils;

	// Handler za vracanje 401 kada klijent sa neodogovarajucim korisnickim imenom i
	// lozinkom pokusa da pristupi resursu
	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

	// Registrujemo authentication manager koji ce da uradi autentifikaciju
	// korisnika za nas
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Definisemo uputstvo za authentication managera koji servis da koristi da
	// izvuce podatke o korisniku koji zeli da se autentifikuje,
	// kao i kroz koji enkoder da provuce lozinku koju je dobio od klijenta u
	// zahtevu da bi adekvatan hash koji dobije kao rezultat bcrypt algoritma
	// uporedio sa onim koji se nalazi u bazi (posto se u bazi ne cuva plain
	// lozinka)
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}

	// Definisemo prava pristupa odredjenim URL-ovima
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				// komunikacija izmedju klijenta i servera je stateless posto je u pitanju REST
				// aplikacija
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

				// sve neautentifikovane zahteve obradi uniformno i posalji 401 gresku
				.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()

				// svim korisnicima dopusti da pristupe putanjama /auth/**, (/h2-console/** ako
				// se koristi H2 baza) i /api/foo
				.authorizeRequests()//.antMatchers("/*").permitAll()
				.antMatchers("/auth/*").permitAll()
				//.antMatchers("/auth/signup").permitAll()
				//.antMatchers("/auth/get").permitAll()
				.antMatchers("/test").permitAll()
//
				// za svaki drugi zahtev korisnik mora biti autentifikovan
				.anyRequest().authenticated().and()

				// umetni custom filter TokenAuthenticationFilter kako bi se vrsila provera JWT
				// tokena umesto cistih korisnickog imena i lozinke (koje radi
				// BasicAuthenticationFilter)
				.addFilterBefore(new TokenAuthenticationFilter(tokenUtils, jwtUserDetailsService),
						BasicAuthenticationFilter.class);

		// zbog jednostavnosti primera
		http.cors().and().csrf().disable();
		
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// TokenAuthenticationFilter ce ignorisati sve ispod navedene putanje
		web.ignoring().antMatchers(HttpMethod.GET, "/", "/webjars/**", "/*.html", "/favicon.ico", "/**/*.html",
				"/**/*.css", "/**/*.js", "/swagger-ui/**", "/v3/api-docs/**");
		
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "https://localhost:3000"));
		configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
		configuration.setAllowCredentials(true);
		configuration.setAllowedHeaders(Arrays.asList("Content-Type", "X-Auth-Token", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
		configuration.setExposedHeaders(Arrays.asList("Content-Type", "X-Auth-Token", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	
	}
	
	

}
