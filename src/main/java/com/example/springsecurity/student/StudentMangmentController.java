package com.example.springsecurity.student;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("mangment/api/v1/students")
public class StudentMangmentController {
   private static final List<Student> studentList =  Arrays.asList(
           new Student(1 , "omar"),
           new Student(2 ,"oussema")  ,
           new Student(3 ,"adel")
    );

    @GetMapping
    public  List<Student> getStudentList() {
        return studentList;
    }
    @PostMapping
    public  void registerNewStudent (@RequestBody Student student){
     System.out.println("get all student");
     System.out.println(student);

    }
    @DeleteMapping(path = "{studentId}")
    public void deleteStudent (@PathVariable ("studentId") Integer studentID){
     System.out.println("delete student");
     System.out.println(studentID);

    }
    @PutMapping(path = "{studentId}")
    public  void  updateStudent (@PathVariable("studentId") Integer studentID , @RequestBody Student student) {
     System.out.println("update studnet");
     System.out.println(String.format("%s %s" ,studentID ,student ));

    }
}
