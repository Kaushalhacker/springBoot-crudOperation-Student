package com.example.createApi.service;

import com.example.createApi.customException.BusinessException;
import com.example.createApi.model.Student;
import com.example.createApi.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;


    public Student addStudent(Student student) {
        try {
            if (student.getFirstName().isEmpty() || student.getFirstName().length() == 0) {
                throw new BusinessException("601", "Please send proper name,it is black");
            }
            Student result = this.studentRepository.save(student);
            return result;
        } catch (IllegalArgumentException e) {
            throw new BusinessException("602", "given student is null " + e.getMessage());
        } catch (Exception e) {
            throw new BusinessException("603", "something went wrong to service layer while adding the data " + e.getMessage());
        }

    }

    public List<Student> fetchStudentlist() {
        try {
            List<Student> empList = studentRepository.findAll();
            if (empList.isEmpty())
                throw new BusinessException("604", "hey list is completly empty,we have nothing return");
            return empList;
        } catch (Exception e) {
            throw new BusinessException("605 ", "something went wrong to service layer while fetching the student" + e.getMessage());

        }

    }

    public Student fetchStudentBYId(int id) {
       try{
            return studentRepository.findById(id).get();
        } catch (IllegalArgumentException e) {
            throw new BusinessException("606", "given student id is null ,please send some id to be search" + e.getMessage());
        } catch (java.util.NoSuchElementException e) {
            throw new BusinessException("607", "given student id doesnot exist in data Base" + e.getMessage());
        }

    }

    public void deleteStudentById(int id) {
        try {
            studentRepository.deleteById(id);
        } catch (IllegalArgumentException e) {
            throw new BusinessException("608", "given student id is null ,please send some id to be search" + e.getMessage());
        }
    }
}

