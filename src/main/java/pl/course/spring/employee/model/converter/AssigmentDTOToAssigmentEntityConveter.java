package pl.course.spring.employee.model.converter;

import lombok.AllArgsConstructor;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;

import pl.course.spring.employee.DTO.AssigmentDTO;
import pl.course.spring.employee.exception.EmployeeNotFoundException;
import pl.course.spring.employee.exception.ProjectNotFoundException;
import pl.course.spring.employee.model.Assigment;
import pl.course.spring.employee.model.Employee;
import pl.course.spring.employee.model.Project;
import pl.course.spring.employee.repository.EmployeeRepository;
import pl.course.spring.employee.repository.ProjectRepository;

@Service
@AllArgsConstructor
public class AssigmentDTOToAssigmentEntityConveter implements Converter<AssigmentDTO, Assigment> {

	private final ProjectRepository projectRepo;
	private final EmployeeRepository employeeRepo;

	@Override
	public Assigment convert(MappingContext<AssigmentDTO, Assigment> context) {
		Employee emp = employeeRepo.findById(context.getSource().getEmployeeId()).orElseThrow(
				() -> new EmployeeNotFoundException(context.getSource().getEmployeeId()));
		Project pro = projectRepo.findById(context.getSource().getProjectId()).orElseThrow(
				() -> new ProjectNotFoundException(context.getSource().getProjectId()));
		
		Assigment assgiment = new Assigment();
		assgiment.setCreatedBy(context.getSource().getCreatedBy());
		assgiment.setCreatedDate(context.getSource().getCreatedDate());
		assgiment.setEmployee(emp);
		assgiment.setLastModifiedBy(context.getSource().getLastModifiedBy());
		assgiment.setLastModifiedDate(context.getSource().getLastModifiedDate());
		assgiment.setId(context.getSource().getId());
		assgiment.setStartDate(context.getSource().getStartDate());
		assgiment.setStopDate(context.getSource().getStopDate());
		assgiment.setProject(pro);
		return assgiment;
	}

}
