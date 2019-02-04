package pl.course.spring.employee.model.search;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import pl.course.spring.employee.model.Employee;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class EmployeeSearchCriteria {

	private String name;
	private String surname;
	private String pesel;
	private String position;
	private Double salaryFrom;
	private Double salaryTo;
	
	public Specification<Employee> toPredciate(){
		return (employeeRoot, cq, cb) -> {
		List<Predicate> predicates = new ArrayList<>();
		if (name != null) {
			predicates.add(cb.like(employeeRoot.get("name"), "%" + name + "%"));
		}
		if (surname != null) {
			predicates.add(cb.like(employeeRoot.get("surname"), "%" + surname + "%"));
		}
		if (pesel != null) {
			predicates.add(cb.like(employeeRoot.get("pesel"), "%" + pesel + "%"));
		}
		if (position != null) {
			predicates.add(cb.like(employeeRoot.get("position"), "%" + position + "%"));
		}
		if (salaryFrom != null) {
			predicates.add(cb.greaterThanOrEqualTo(employeeRoot.get("salary"), salaryFrom));
		}
		if (salaryTo!= null) {
			predicates.add(cb.lessThanOrEqualTo(employeeRoot.get("salary"), salaryTo));
		}
		return cb.and(predicates.toArray(new Predicate[predicates.size()]));
	};
	}
}
