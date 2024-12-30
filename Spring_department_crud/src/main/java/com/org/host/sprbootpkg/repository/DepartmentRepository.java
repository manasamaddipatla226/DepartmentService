package com.org.host.sprbootpkg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.host.sprbootpkg.entity.DepartmentDAO;
@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentDAO, Long> {

	List<DepartmentDAO> findByName(String name);
	List<DepartmentDAO> findByDescription(String description);

	
	

}
