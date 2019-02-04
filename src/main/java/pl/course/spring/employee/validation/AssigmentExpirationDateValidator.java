package pl.course.spring.employee.validation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import pl.course.spring.employee.DTO.AssigmentDTO;
import pl.course.spring.employee.exception.ProjectNotFoundException;
import pl.course.spring.employee.model.Project;
import pl.course.spring.employee.repository.ProjectRepository;
import pl.course.spring.employee.validation.annotation.AssigmentExpirationDate;

@Service
@AllArgsConstructor
public class AssigmentExpirationDateValidator implements
		ConstraintValidator<AssigmentExpirationDate, AssigmentDTO> {

	private final ProjectRepository projectRepo;

	@Override
	public boolean isValid(AssigmentDTO dto, ConstraintValidatorContext arg1) {
		Project pro = projectRepo.findById(dto.getProjectId()).orElseThrow(
				() -> new ProjectNotFoundException(dto.getProjectId()));
		return !(dto.getStopDate().after(pro.getExpirationDate()) || dto.getStartDate().after(
				pro.getExpirationDate()));
	}

}
