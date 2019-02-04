package pl.course.spring.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ProjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 7908728003779117574L;

	private final long id;

	public ProjectNotFoundException(long id) {
		this.id = id;
	}

	@Override
	public String getMessage() {
		return "Project with id: " + id + " not found!";
	}
}
