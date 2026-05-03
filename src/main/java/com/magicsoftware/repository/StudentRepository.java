package com.magicsoftware.repository;

import com.magicsoftware.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * Repository interface
 *
 * What - connects applications with database
 *
 * Why
 *  - we don't write SQL manually
 *  - Spring provides ready-made methods
 */

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    /**
     * Custom method
     *
     * What
     * - Finds student by email
     *
     * Why:
     * - Used to check duplicate email before saving
     *
     * How it works
     * - Spring automatically generates query based on method name
     */
    Optional<Student> findByEmail(String email);


    Page<Student> findAll(Pageable pageable);

}
