package pl.course.spring.employee.model.search;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.Predicate;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.jpa.domain.Specification;

import pl.course.spring.employee.model.Project;
@Data
@NoArgsConstructor
public class ProjectSearchCriteria {
	private String name;
	private Integer priceFrom;
	private Integer priceTo;
	private Date expiratonDateFrom;
	private Date expirationDateTo;

	public Specification<Project> toPredciate(){
		return (projectRoot, cq, cb) -> {
		List<Predicate> predicates = new ArrayList<>();
		if (name != null) {
			predicates.add(cb.like(projectRoot.get("name"), "%" + name + "%"));
		}
	
		if (priceFrom != null) {
			predicates.add(cb.greaterThanOrEqualTo(projectRoot.get("price"), priceFrom));
		}
		if (priceTo != null) {
			predicates.add(cb.lessThanOrEqualTo(projectRoot.get("price"),priceTo));
		}
		if (expiratonDateFrom != null) {
			predicates.add(cb.greaterThanOrEqualTo(projectRoot.get("expiratonDate"), expiratonDateFrom));
		}
		if (expirationDateTo != null) {
			predicates.add(cb.lessThanOrEqualTo(projectRoot.get("expiratonDate"),expirationDateTo));
		}
		return cb.and(predicates.toArray(new Predicate[predicates.size()]));
};}}
