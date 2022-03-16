package com.app.ua.controller;

import com.app.ua.entity.StudentEntity;
import com.app.ua.model.Student;
import com.app.ua.service.StudentService;
import com.app.ua.service.UpdateTeamScore;
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
    @Autowired
    private UpdateTeamScore updateTeamScore;

    @PostMapping("/save")
    public void createStudent(@RequestBody StudentEntity student,
                              @RequestParam Long teamId) {
        student.setScore(0.0);
        log.info("Save student " + student.getName() + " " + student.getLastName());
        studentService.createStudent(student, teamId);
    }

    @PutMapping("/update")
    public ResponseEntity updateStudent(@RequestBody StudentEntity student,
                                        @RequestParam Long studentId) {
        log.info(studentId + "dd");
        updateTeamScore.updateTeam();
        try {
            Double score = student.getScore();
            return ResponseEntity.ok(studentService.addingScore(studentId, score));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @GetMapping("/findAll")
    public List<Student> findAllStudents() {
        return studentService.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        log.info("Student will be removed " + id);
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }


}
