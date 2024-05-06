package telran.java52.student.service;

import java.util.List;
import java.util.Set;
//import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import telran.java52.student.dao.StudentRepository;
import telran.java52.student.dto.ScoreDto;
import telran.java52.student.dto.StudentAddDto;
import telran.java52.student.dto.StudentDto;
import telran.java52.student.dto.StudentUpdateDto;
import telran.java52.student.dto.exception.StudentNotFoundException;
import telran.java52.student.model.Student;

@Component
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

	final ModelMapper modelMapper;

	final StudentRepository studentRepository;

	@Override
	public Boolean addStudent(StudentAddDto studentAddDto) {
		if (studentRepository.existsById(studentAddDto.getId()))
			return false;
		Student student = modelMapper.map(studentAddDto, Student.class);
		// new Student(studentAddDto.getId(), studentAddDto.getName(),
		// studentAddDto.getPassword());
		studentRepository.save(student);
		return true;
	}

	@Override
	public StudentDto findStudent(Long id) {
		// studentRepository.
		Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
		return modelMapper.map(student, StudentDto.class);
	}

	@Override
	public StudentDto removeStudent(Long id) {
		Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
		StudentDto reStudentDto = new StudentDto(id, student.getName(), student.getScores());
		studentRepository.deleteById(id);
		return reStudentDto;

	}

	@Override
	public StudentAddDto updateStudent(Long id, StudentUpdateDto studentUpdateDto) {
		if(studentRepository.existsById(id)) {
			Student student = new Student(id, studentUpdateDto.getName(), studentUpdateDto.getPassword());
			studentRepository.save(student);
			return modelMapper.map(student, StudentAddDto.class);
					//new StudentAddDto(id, studentUpdateDto.getName(), studentUpdateDto.getPassword());
	} return null;
		}

	@Override
	public Boolean addScore(Long id, ScoreDto scoreDto) {
		Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
		boolean res = student.addScore(scoreDto.getExamName(), scoreDto.getScore());
		studentRepository.save(student);
		return res;
	}

	@Override
	public List<StudentDto> findStudentsByName(String name) {

		System.out.println(name);
		return studentRepository.findByNameIgnoreCase(name)
				.map(s -> modelMapper.map(s, StudentDto.class)).toList();
	}//new StudentDto(s.getId(), s.getName(), s.getScores()))

	@Override
	public Long getStudentsNameQuantityLong(Set<String> names) {
		return studentRepository.countByNameIgnoreCaseIn(names);

	}

	@Override
	public List<StudentDto> getStudentsByExamMinScore(String exam, Integer minScore) {

		return studentRepository.getByExam(exam, minScore)
				.map(s -> modelMapper.map(s, StudentDto.class))
				//.peek(s->System.out.println(s))
				.toList();
	}

}
