package telran.java52.student.dao;

import java.util.Set;
import java.util.stream.Stream;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import telran.java52.student.model.Student;

public interface StudentRepository extends MongoRepository<Student, Long>{
 
 
  Stream <Student> getAllBy();
  Stream <Student> findByNameIgnoreCase(String name);
  Long countByNameIgnoreCaseIn(Set<String> names);

   // Это запрос нумеррованный указывает на  порядковый номар аргумента в функции
  @Query("{ 'scores.?0': { $gt: ?1 } }")
  Stream<Student> getByExam(String exam, Integer minScore);
}
