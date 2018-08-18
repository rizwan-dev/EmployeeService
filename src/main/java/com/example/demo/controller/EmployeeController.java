package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.BaseResponse;
import com.example.demo.model.Employee;
import com.example.demo.repository.NoteRepository;

@RestController
@RequestMapping("myapp/api")
public class EmployeeController {

	@Autowired
	NoteRepository mNoteRepository;

	@PostMapping("/employees")
	public ResponseEntity<BaseResponse> addEmployees(@RequestBody List<Employee> employees) {
		mNoteRepository.saveAll(employees);
		return new ResponseEntity<BaseResponse>(new BaseResponse("Success", "Record inserted successfully"),
				HttpStatus.CREATED);
	}

	@PostMapping("/employees/single")
	public ResponseEntity<BaseResponse> addEmployee(@RequestBody Employee employee) {
		mNoteRepository.save(employee);
		return new ResponseEntity<BaseResponse>(new BaseResponse("Success", "Record inserted successfully"),
				HttpStatus.CREATED);
	}

	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> employees = mNoteRepository.findAll();
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getAllEmployee(@PathVariable("id") long id ) {
		Optional<Employee> employee = mNoteRepository.findById(id);
		if(!employee.isPresent()) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Employee>(employee.get(), HttpStatus.OK);
	}
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<BaseResponse> deleteEmployee(@PathVariable("id") long id ) {
		Optional<Employee> employee = mNoteRepository.findById(id);
		if(!employee.isPresent()) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		mNoteRepository.deleteById(id);
		return new ResponseEntity<BaseResponse>(new BaseResponse("Success","Record deleted"), HttpStatus.OK);
	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable(value = "id") long id,@Valid @RequestBody Employee employee ) {
		Employee emp = mNoteRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
		emp.setAddress(employee.getAddress());
		emp.setName(employee.getName());
		emp.setDesignation(employee.getDesignation());
		emp.setSalary(employee.getSalary());
		return new ResponseEntity<Employee>(emp, HttpStatus.OK);
	}

}
