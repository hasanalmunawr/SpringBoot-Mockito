package com.hasanalmunawr.SpringBoot_Mockito.student;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
class StudentController {

    private final StudentService studentService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void addStudent(@RequestBody StudentRequestDto student) {
        studentService.save(student);
    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        return ResponseEntity.ok().body(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable int id) {
        return ResponseEntity.ok().body(studentService.findById(id));
    }

    @DeleteMapping(path = "/{id}")
    public void deleteStudentById(@PathVariable int id) {
        studentService.deleteById(id);
    }


}
