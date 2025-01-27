package com.bruceycode.My_Rest_Api.Repository;

import com.bruceycode.My_Rest_Api.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository
        extends JpaRepository<Student, Long> {
}
