package telran.java52.student.dto.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)

public class StudentNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

}