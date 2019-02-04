package pl.course.spring.employee.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		//
				.antMatchers(HttpMethod.POST, "/employee/**").authenticated()
				//
				.antMatchers(HttpMethod.PUT, "/employee/**").authenticated()
				//
				.antMatchers(HttpMethod.DELETE, "/employee/**").authenticated()
				//
				.antMatchers(HttpMethod.POST, "/project/**").authenticated()
				//
				.antMatchers(HttpMethod.PUT, "/project/**").authenticated()
				//
				.antMatchers(HttpMethod.DELETE, "/project/**").authenticated()
				//
				.anyRequest().permitAll().and().httpBasic()//
		;
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password("{noop}password").roles("USER")
		.and().withUser("mateusz").password("{noop}password").roles("USER");
	}

}
