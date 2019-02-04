package pl.course.spring.employee.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjectDTO {

	private Long id;
	private String name;
	private int price;
	private Date expirationDate;
	
	
	private String createdBy;
	private String lastModifiedBy;
	private Date createdDate;
	private Date lastModifiedDate;
}
