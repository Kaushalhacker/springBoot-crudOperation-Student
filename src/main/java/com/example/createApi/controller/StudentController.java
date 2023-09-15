package com.example.createApi.controller;

import com.example.createApi.customException.BusinessException;
import com.example.createApi.customException.ControllerException;
import com.example.createApi.model.Student;
import com.example.createApi.repository.StudentRepository;
import com.example.createApi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;



    @PostMapping("/students")
    public ResponseEntity<?> addStudent(@RequestBody Student student) {
        try {
            Student student1 =studentService.addStudent(student);
           // System.out.println(student1);
            return new  ResponseEntity<Student>(student1,HttpStatus.CREATED);
        } catch (BusinessException e) {
              ControllerException controllerException=  new ControllerException(e.getErrorCode(),e.getErrorMessage());
            return new  ResponseEntity<ControllerException>(controllerException,HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            ControllerException controllerException=new ControllerException("611","something went wrong in controller");
            return new ResponseEntity<ControllerException>(controllerException,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/students")
    public List<Student> getJobs()
    {
        return studentService.fetchStudentlist();
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<?> fetchStudentById(@PathVariable("id") int id) {
        try {
            Student student=studentService.fetchStudentBYId(id);
            return new ResponseEntity<Student>(student,HttpStatus.OK);
        }catch (BusinessException e){
            ControllerException controllerException=  new ControllerException(e.getErrorCode(),e.getErrorMessage());
            return new  ResponseEntity<ControllerException>(controllerException,HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
        ControllerException controllerException=new ControllerException("612","something went wrong in controller");
        return new ResponseEntity<ControllerException>(controllerException,HttpStatus.BAD_REQUEST);
    }

    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable("id") int id) {
        try {
            this.studentService.deleteStudentById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Student student) {
        Optional<Student> optionalStudent=studentRepository.findById(id);

        if(!optionalStudent.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Student student1=optionalStudent.get();
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setNumber(student.getNumber());

        Student savedStudent=studentRepository.save(student1);
        return ResponseEntity.ok(savedStudent);
    }

}
