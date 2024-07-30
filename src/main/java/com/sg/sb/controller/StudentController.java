package com.sg.sb.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sg.sb.entity.Student;
import com.sg.sb.repo.StudentRepo;

@RestController
public class StudentController {
	@Autowired
	StudentRepo studentRepo;

	@PostMapping("/api/student")
	public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
		return new ResponseEntity<>(studentRepo.save(student), HttpStatus.CREATED);
	}

	@GetMapping("/api/getStudent")
	public ResponseEntity<List<Student>> getStudents() {
		return new ResponseEntity<>(studentRepo.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/getStudent/{id}")
	public ResponseEntity<Student> getStudents(@PathVariable long id) {
		Optional<Student> student =studentRepo.findById(id);
		if(student.isPresent()) {
			return new ResponseEntity<>(student.get(), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
		}
	}
	
	
	@PutMapping("/api/getStudent/{id}")
	public ResponseEntity<Student> UpdateStudents(@PathVariable long id,@RequestBody Student stud) {
		Optional<Student> student =studentRepo.findById(id);
		if(student.isPresent()) {
			student.get().setName(stud.getName());
			student.get().setEmail(stud.getEmail());
			student.get().setAddress(stud.getAddress());
			return new ResponseEntity<>(studentRepo.save(student.get()), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	@DeleteMapping("/api/getStudent/{id}")
	public ResponseEntity<Student> deleteStudents(@PathVariable long id) {
		Optional<Student> student =studentRepo.findById(id);
		if(student.isPresent()) {
			studentRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
		}
	}
	
	
}
