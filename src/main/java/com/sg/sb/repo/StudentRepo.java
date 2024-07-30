package com.sg.sb.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sg.sb.entity.Student;

public interface StudentRepo  extends JpaRepository<Student, Long>{
	

}
