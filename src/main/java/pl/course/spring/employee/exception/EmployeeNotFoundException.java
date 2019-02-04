package pl.course.spring.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 7186207545808246958L;
	private final long id;

	public EmployeeNotFoundException(long id) {
		this.id = id;
	}

	@Override
	public String getMessage() {
		return "Employee with id: " + id + " not found!";
	}

}
