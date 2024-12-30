package com.org.host.sprbootpkg.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.host.sprbootpkg.entity.DepartmentDAO;
import com.org.host.sprbootpkg.repository.DepartmentRepository;
import com.org.host.sprbootpkg.response.MainResponse;

@Service
public class DepartmentService {

//	private static final Object DepartmentDAO = null;
	@Autowired
	private DepartmentRepository departmentRepository;

//	create Department
	public MainResponse createDepartment(DepartmentDAO department) {

		MainResponse mainResponse = new MainResponse();

		if (department.getName() == null || department.getName().trim().isEmpty()) {
			mainResponse.setMessage("name cannot be null or empty");
			mainResponse.setData(null);
		} else if (department.getDescription() == null || department.getDescription().trim().isEmpty()) {
			mainResponse.setMessage("description cannot be null or empty");
			mainResponse.setData(null);
		} else {
			DepartmentDAO depres1 = departmentRepository.save(department);
			mainResponse.setMessage("success");
			mainResponse.setData(depres1);
		}

		return mainResponse;

	}

	public MainResponse getDepartment(long id) {
		MainResponse mainResponse = new MainResponse();

		Optional<DepartmentDAO> depres = departmentRepository.findById(id);

		DepartmentDAO dep = null;
		String error = null;
		if (depres != null && depres.isPresent()) {
			dep = depres.get();
			mainResponse.setMessage("Success");
			mainResponse.setData(dep);
		} else {
			error = "Data is not found with " + id;
			mainResponse.setMessage("Error");
			mainResponse.setData(error);
		}

		return mainResponse;
	}

	public DepartmentDAO updateDepartment(long id, DepartmentDAO department) {
		Optional<DepartmentDAO> depresOptional = departmentRepository.findById(id);

		DepartmentDAO department1 = depresOptional.get();
		if (department.getName() != null && !department.getName().isEmpty()) {
			department1.setName(department.getName());
		}
		if (department.getDescription() != null && !department.getDescription().isEmpty()) {
			department1.setDescription(department.getDescription());
		}

		DepartmentDAO depres = departmentRepository.save(department1);
		return depres;
		// TODO Auto-generated method stub
	}

	public String deleteDepartment(long id) {
		Optional<DepartmentDAO> depresOptional = departmentRepository.findById(id);

		if (depresOptional.isPresent()) {
			DepartmentDAO department2 = depresOptional.get();
			departmentRepository.delete(department2);
			// TODO Auto-generated method stub
			return "data deleted successfully";
		} else {
			return "data not found";
		}

	}

////	constructor injection for repository
//	public DepartmentService(DepartmentRepository departmentRepository) {
//		this.departmentRepository = departmentRepository;
//	}

	public MainResponse getAlldepartments() {
		MainResponse mainResponse = new MainResponse();
		List<DepartmentDAO> departments = departmentRepository.findAll();
		if (departments.isEmpty()) {
			mainResponse.setMessage("no data");
			mainResponse.setData(null);
			return mainResponse;
		} else {
			mainResponse.setMessage("Success");
			mainResponse.setData(departments);
			return mainResponse;
		}
		// TODO Auto-generated method stub
	}

	public MainResponse getDepartmentByName(String name) {
		MainResponse mainResponse = new MainResponse();
		List<DepartmentDAO> depres = departmentRepository.findByName(name);

		mainResponse.setMessage("success");
		mainResponse.setData(depres);
		return mainResponse;
	}

	public MainResponse getDepartmentByDesc(String description) {
		MainResponse mainResponse = new MainResponse();
		List<DepartmentDAO> depres1 = departmentRepository.findByDescription(description);

		List<DepartmentDAO> deptResp = new ArrayList<>();
		for (DepartmentDAO depres2 : depres1) {
			if (depres2.getDescription() != null && depres2.getDescription().equals("java developer")) {
				deptResp.add(depres2);
			}

		}

		mainResponse.setMessage("success");
		mainResponse.setData(deptResp);
		return mainResponse;

	}

}
