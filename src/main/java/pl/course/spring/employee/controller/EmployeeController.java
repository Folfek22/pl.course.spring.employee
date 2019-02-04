package pl.course.spring.employee.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.course.spring.employee.DTO.EmployeeDTO;
import pl.course.spring.employee.exception.EmployeeNotFoundException;
import pl.course.spring.employee.model.Employee;
import pl.course.spring.employee.model.search.EmployeeSearchCriteria;
import pl.course.spring.employee.repository.EmployeeRepository;

@RestController
@AllArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {

	private final EmployeeRepository employeeRepository;
	private final ModelMapper mapper;

	@GetMapping("/")
	public ResponseEntity<List<EmployeeDTO>> getAllEmployee(
			@ModelAttribute EmployeeSearchCriteria criteria) {
		List<Employee> employees = employeeRepository.findAll(criteria.toPredciate());
		List<EmployeeDTO> result = employees.stream().map(e -> mapper.map(e, EmployeeDTO.class))
				.collect(Collectors.toList());
		return new ResponseEntity<List<EmployeeDTO>>(result, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable("id") Long id) {
		Employee emp = employeeRepository.findById(id).orElseThrow(
				() -> new EmployeeNotFoundException(id));
		EmployeeDTO dto = mapper.map(emp, EmployeeDTO.class);
		return new ResponseEntity<EmployeeDTO>(dto, HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) {
		Employee emp = mapper.map(employeeDTO, Employee.class);
		Employee saved = employeeRepository.save(emp);
		return new ResponseEntity<EmployeeDTO>(mapper.map(saved, EmployeeDTO.class),
				HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody @Valid EmployeeDTO employeeDTO,
			@PathVariable("id") Long id) {
		if (!id.equals(employeeDTO.getId())) {
			throw new IllegalArgumentException();
		}
		Employee toUpdate = employeeRepository.findById(id).orElseThrow(
				() -> new EmployeeNotFoundException(id));
		toUpdate.setName(employeeDTO.getName());
		toUpdate.setSurname(employeeDTO.getSurname());
		toUpdate.setPesel(employeeDTO.getPesel());
		toUpdate.setPosition(employeeDTO.getPosition());
		toUpdate.setSalary(employeeDTO.getSalary());
		Employee saved = employeeRepository.save(toUpdate);
		return new ResponseEntity<EmployeeDTO>(mapper.map(saved, EmployeeDTO.class), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable("id") Long id) {
		if(!employeeRepository.existsById(id)){
			throw new EmployeeNotFoundException(id);
		}
		employeeRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
