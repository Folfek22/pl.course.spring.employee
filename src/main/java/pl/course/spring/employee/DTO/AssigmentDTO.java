package pl.course.spring.employee.DTO;

import java.util.Date;

import pl.course.spring.employee.validation.annotation.AssigmentExpirationDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@AssigmentExpirationDate
public class AssigmentDTO {

	private Long id;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date startDate;
	@JsonFormat(pattern="yyyy-MM-dd")
	//@AssigmentExpirationDate
	private Date stopDate;
	private long employeeId;
	private long projectId;

	private String createdBy;
	private String lastModifiedBy;
	private Date createdDate;
	private Date lastModifiedDate;
}
