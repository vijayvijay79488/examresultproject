package com.example.examresult.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.examresult.model.studentrevalutionRecords;

@Repository
public interface studentrevalutionrecordsrepo extends JpaRepository<studentrevalutionRecords, Long> {
	List<studentrevalutionRecords> findByRegisteredAndSemester(String registered, String semester);

	List<studentrevalutionRecords> findByRegistered(String registered);

	Optional<studentrevalutionRecords> findFirstByRegisteredAndSemester(String registered, String semester);

//	studentrevalutionRecords findByRegisteredAndSemesters(String registered, String semester);
//	List<studentrevalutionRecords> fingByRegistered(String registered);
}
