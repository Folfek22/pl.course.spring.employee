package pl.course.spring.employee.config;

import java.util.Set;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import pl.course.spring.employee.services.GetCurrentUserForAuditService;

@Configuration
@EnableJpaAuditing
public class ApplicationConfiguration {

	@Bean
	public AuditorAware<String> auditorProvider() {
		return new GetCurrentUserForAuditService();
	}

	@Bean
	public ModelMapper modelMapper(Set<Converter> allConverters) {
		ModelMapper mapper = new ModelMapper();
		allConverters.forEach(mapper::addConverter);
		return mapper;
	}
}
