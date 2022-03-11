package com.app.ua.controller;

import com.app.ua.entity.StudentEntity;
import com.app.ua.model.Student;
import com.app.ua.service.StudentService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author Vladislav Domaniewski 04
 */

@Log
@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping()
    public ResponseEntity createStudent(@RequestBody StudentEntity student,
                                       @RequestParam  Integer teamId) {
        try {
            return ResponseEntity.ok(studentService.createStudent(student, teamId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @PutMapping
    public ResponseEntity updateStudent(@RequestParam Integer id) {
        try {
            return ResponseEntity.ok(studentService.complete(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @GetMapping("/findAll")
    public List<Student> findAllStudents() {
        log.info("Ok students");
        return studentService.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStudent (@PathVariable Integer id) {
        log.info("Student will be removed "  + id);
        studentService.deleteStudent(id);
    }


}
