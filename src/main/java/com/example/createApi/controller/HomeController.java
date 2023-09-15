package com.example.createApi.controller;

import com.example.createApi.model.Student;
import com.example.createApi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students/{first}")
    public ResponseEntity<List<Student>> fetchByStudentFirstName(@PathVariable ("first") String firstName){
        List<Student> student= (List<Student>) studentService.fetchByStudentFirstName(firstName);
        return (ResponseEntity<List<Student>>) student;
    }

}
