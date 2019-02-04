package pl.course.spring.employee.DTO;

import java.util.Date;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeDTO {

	private Long id;
	private String name;
	private String surname;
	private String pesel;
	private double salary;
	private String position;
	
	private String createdBy;
	private String lastModifiedBy;
	private Date createdDate;
	private Date lastModifiedDate;
}
