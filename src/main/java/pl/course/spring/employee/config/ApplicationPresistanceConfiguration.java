package pl.course.spring.employee.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages ="pl.course.spring.employee.repository")
public class ApplicationPresistanceConfiguration {

}
