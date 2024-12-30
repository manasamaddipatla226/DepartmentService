package com.org.host.sprbootpkg.controller;

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

import com.org.host.sprbootpkg.entity.DepartmentDAO;
import com.org.host.sprbootpkg.response.MainResponse;
import com.org.host.sprbootpkg.service.DepartmentService;

@RestController
@RequestMapping("/api")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

//	create new department

	@PostMapping("/createdept")
	public MainResponse createDepartment(@RequestBody DepartmentDAO department) {

		MainResponse mainResponse = departmentService.createDepartment(department);
		return mainResponse;

	}

	@GetMapping("/getdept/{id}")
	public MainResponse getDepartment(@PathVariable(name = "id") long id) {
		MainResponse depres = departmentService.getDepartment(id);
		return depres;

	}

	@PutMapping("/putdept/{id}")
	public DepartmentDAO updateDepartment(@PathVariable(name = "id") long id, @RequestBody DepartmentDAO department) {
		DepartmentDAO depres = departmentService.updateDepartment(id, department);
		return depres;
	}

	@DeleteMapping("/Deletedept/{id}")
	public String deleteDepartment(@PathVariable(name = "id") long id) {
		String depres = departmentService.deleteDepartment(id);
		return depres;

	}

//	constructor injection for department controller
	public DepartmentController(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	@GetMapping("/findall")
	public MainResponse getAllDepartments() {
		return departmentService.getAlldepartments();

	}

	@GetMapping("/getdeptbyname/{name}")
	public MainResponse getDepartmentByName(@PathVariable(name = "name") String name) {

		MainResponse depres = departmentService.getDepartmentByName(name);
		return depres;
	}

	@GetMapping("/getdeptbydesc/{description}")
	public ResponseEntity<MainResponse> getDepartmentByDesc(@PathVariable(name = "description") String description) {
		MainResponse depres1 = departmentService.getDepartmentByDesc(description);
		return new ResponseEntity<>(depres1, HttpStatus.OK);
	}

}
