package com.example.createApi.repository;

import com.example.createApi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {


    @Query("select u from student where u.firstName=?1")
   List<Student> findByFirstName(String firstName);
}
