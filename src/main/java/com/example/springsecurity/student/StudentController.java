package com.example.springsecurity.student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {
    private  final  static List<Student>  STUDENTS = Arrays.asList(
      new Student(1 , "omar"),
      new Student(2 ,"oussema")  ,
      new Student(3 ,"adel")
    );
@GetMapping(path = "{studentId}")
    public  Student GetStudent ( @PathVariable("studentId")Integer studentId ){

    return STUDENTS.stream()
            .filter(student ->studentId.equals(student.getStudentId()))
            .findFirst()
            .orElseThrow( () -> new IllegalStateException("Student" + studentId +"no exist ")) ;

}
//aprendre plus sur les stream
// BASIC AUTH OVERVIEW
}

