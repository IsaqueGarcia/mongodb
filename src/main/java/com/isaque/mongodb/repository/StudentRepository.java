package com.isaque.mongodb.repository;

import com.isaque.mongodb.dto.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends MongoRepository<Student, String> {

    Optional<Student> findStudentByEmail(String email);

    @Query("show dbs;")
    List<String> showDbs();
}
