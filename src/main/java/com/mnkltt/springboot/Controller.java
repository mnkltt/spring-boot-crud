package com.mnkltt.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("student")
public class Controller {

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping
    public ResponseEntity createStudent(@RequestBody Student student) {
        Student savedStudent = studentRepository.save(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity updateStudent(@PathVariable("id") Integer id, @RequestBody Student updatedStudent) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            student.setName(updatedStudent.getName());
            student.setEmail(updatedStudent.getEmail());

            Student savedStudent = studentRepository.save(student);
            return new ResponseEntity<>(savedStudent, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("")
    public List<Student> getAllStudents() {
        return (List<Student>) studentRepository.findAll();
    }

    @DeleteMapping("{id}")
    public String deleteStudentById(@PathVariable("id") Integer id) {
        studentRepository.deleteById(id);
        return "Deleted successfully";
    }

    @GetMapping("{id}")
    public Optional<Student> getStudentById(@PathVariable("id") Integer id) {
        return studentRepository.findById(id);
    }

    @GetMapping("name")
    public List<Student> getStudentByName(@RequestParam String name) {
        return studentRepository.findByNameContaining(name);
    }

}
