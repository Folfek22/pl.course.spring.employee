package pl.course.spring.employee.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.course.spring.employee.DTO.AssigmentDTO;
import pl.course.spring.employee.model.Assigment;
import pl.course.spring.employee.repository.AssigmentRepository;


@RestController
@AllArgsConstructor
@RequestMapping("/assigment")
public class AssigmentController {

	private ModelMapper mapper;
	private AssigmentRepository assigmentRepo;
	
	@GetMapping("/")
	public ResponseEntity<List<AssigmentDTO>> getAllAssigement(){
		List<Assigment> assigments = assigmentRepo.findAll();
		List<AssigmentDTO> result = assigments.stream().map(e -> mapper.map(e, AssigmentDTO.class))
				.collect(Collectors.toList());
		return new ResponseEntity<List<AssigmentDTO>>(result, HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<AssigmentDTO> addAssigment(@RequestBody @Valid AssigmentDTO assigmentDTO){
		Assigment assgiment = mapper.map(assigmentDTO, Assigment.class);
		Assigment saved = assigmentRepo.save(assgiment);
		return new ResponseEntity<AssigmentDTO>(mapper.map(saved, AssigmentDTO.class),
				HttpStatus.CREATED);
	}
}
