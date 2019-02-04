package pl.course.spring.employee.model.search;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import pl.course.spring.employee.model.Assigment;
import pl.course.spring.employee.model.Employee;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class AssigmentSearchCriteria {

	private Date startDateFrom;
	private Date startDateTo;
	private Date stopDateFrom;
	private Date stopDateTo;
	private Long employeeId;
	private Long projectId;
	
	public Specification<Assigment> toPredciate(){
		return (assigmentRoot, cq, cb) -> {
		List<Predicate> predicates = new ArrayList<>();
		if (startDateFrom != null) {
			predicates.add(cb.greaterThanOrEqualTo(assigmentRoot.get("startDate"), startDateFrom));
		}
		if (startDateTo != null) {
			predicates.add(cb.lessThanOrEqualTo(assigmentRoot.get("startDate"),startDateTo));
		}
		if (stopDateFrom != null) {
			predicates.add(cb.greaterThanOrEqualTo(assigmentRoot.get("stopDate"), stopDateFrom));
		}
		if (stopDateTo != null) {
			predicates.add(cb.lessThanOrEqualTo(assigmentRoot.get("stopDate"),stopDateTo));
		}
	
		
		return cb.and(predicates.toArray(new Predicate[predicates.size()]));
	};
	}
}
