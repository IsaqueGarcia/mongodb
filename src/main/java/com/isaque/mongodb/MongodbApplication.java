package com.isaque.mongodb;

import com.isaque.mongodb.dto.Address;
import com.isaque.mongodb.dto.Gender;
import com.isaque.mongodb.dto.Student;
import com.isaque.mongodb.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class MongodbApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongodbApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository repository, MongoTemplate template){
		return args -> {
			Address address = new Address("Brazil","Sao Paulo","02469000");
			String email = "isaque@gmail.com";
			Student student = new Student(
					"Isaque",
					"Garcia",
					email,
					Gender.MALE,
					address,
					List.of("IT","Math"),
					BigDecimal.TEN,
					LocalDateTime.now()
			);

			repository.findStudentByEmail(email).ifPresentOrElse(s -> {
				System.out.println("Already exists " + s);
			}, () ->  {
				System.out.println("Inserting student " + student);
				repository.insert(student);
			});

			//usingMongoTemplateAndQuery(repository, template, email, student);

		};
	}

	private static void usingMongoTemplateAndQuery(StudentRepository repository, MongoTemplate template, String email, Student student) {
		Query query = new Query();
		query.addCriteria(Criteria.where("email").is(email));

		List<Student> students = template.find(query,Student.class);

		if(students.size() > 1){
			throw new IllegalStateException("Many students with the same e-mail: " + email);
		}

		if(students.isEmpty()){
			System.out.println("Inserting student " + student);
			repository.insert(student);
		}else{
			System.out.println("Already exists " + student);
		}
	}
}
