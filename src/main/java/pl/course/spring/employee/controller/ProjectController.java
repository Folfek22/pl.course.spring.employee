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

import pl.course.spring.employee.DTO.ProjectDTO;
import pl.course.spring.employee.exception.ProjectNotFoundException;
import pl.course.spring.employee.model.Project;
import pl.course.spring.employee.model.search.ProjectSearchCriteria;
import pl.course.spring.employee.repository.ProjectRepository;



@RestController
@AllArgsConstructor
@RequestMapping("/project")
public class ProjectController {
	private final ProjectRepository ProjectRepository;
	private final ModelMapper mapper;

	@GetMapping("/")
	public ResponseEntity<List<ProjectDTO>> getAllProject(
			@ModelAttribute ProjectSearchCriteria criteria) {
		List<Project> Projects = ProjectRepository.findAll(criteria.toPredciate());
		List<ProjectDTO> result = Projects.stream().map(e -> mapper.map(e, ProjectDTO.class))
				.collect(Collectors.toList());
		return new ResponseEntity<List<ProjectDTO>>(result, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProjectDTO> getProjectById(@PathVariable("id") Long id) {
		Project emp = ProjectRepository.findById(id).orElseThrow(
				() -> new ProjectNotFoundException(id));
		ProjectDTO dto = mapper.map(emp, ProjectDTO.class);
		return new ResponseEntity<ProjectDTO>(dto, HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<ProjectDTO> addProject(@RequestBody @Valid ProjectDTO ProjectDTO) {
		Project emp = mapper.map(ProjectDTO, Project.class);
		Project saved = ProjectRepository.save(emp);
		return new ResponseEntity<ProjectDTO>(mapper.map(saved, ProjectDTO.class),
				HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProjectDTO> updateProject(@RequestBody @Valid ProjectDTO ProjectDTO,
			@PathVariable("id") Long id) {
		if (!id.equals(ProjectDTO.getId())) {
			throw new IllegalArgumentException();
		}
		Project toUpdate = ProjectRepository.findById(id).orElseThrow(
				() -> new ProjectNotFoundException(id));
		toUpdate.setName(ProjectDTO.getName());
		toUpdate.setPrice(ProjectDTO.getPrice());
		toUpdate.setExpirationDate(ProjectDTO.getExpirationDate());
		Project saved = ProjectRepository.save(toUpdate);
		return new ResponseEntity<ProjectDTO>(mapper.map(saved, ProjectDTO.class), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProject(@PathVariable("id") Long id) {
		if(!ProjectRepository.existsById(id)){
			throw new ProjectNotFoundException(id);
		}
		ProjectRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
