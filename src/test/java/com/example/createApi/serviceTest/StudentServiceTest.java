package com.example.createApi.serviceTest;

import com.example.createApi.model.Student;
import com.example.createApi.repository.StudentRepository;
import com.example.createApi.service.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class StudentServiceTest {

    @InjectMocks
    StudentService studentService;

    @Mock
    StudentRepository studentRepository;

    @Test
    public void fetchStudentBYIdTest(){
        when(studentRepository.findById(1)).thenReturn(createStudentStub());
        Student student=studentService.fetchStudentBYId(1);
        assertEquals(student.getFirstName(),"Shyam");
    }


    private Optional<Student> createStudentStub(){
        Student student=Student.builder().id(1).firstName("Shyam").lastName("Kumar").number(7897894560L).build();
        return Optional.ofNullable(student);
    }
}
