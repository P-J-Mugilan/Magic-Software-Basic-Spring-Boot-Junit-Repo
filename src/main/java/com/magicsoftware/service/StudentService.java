package com.magicsoftware.service;


import com.magicsoftware.dto.APIResponse;
import com.magicsoftware.dto.StudentListResponseDTO;
import com.magicsoftware.dto.StudentRequestDTO;
import com.magicsoftware.dto.StudentResponseDTO;
import com.magicsoftware.model.Student;
import com.magicsoftware.exception.ConflictException;
import com.magicsoftware.exception.ResourceNotFoundException;
import com.magicsoftware.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Layer
 *
 * What
 * - Contains business logic
 *
 * Why
 * - Keeps controller clean
 * - Handles validation, mapping, and rules
 */

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    // register

    public APIResponse<StudentResponseDTO> register (
            StudentRequestDTO request
    ) {

        // checks duplicate email
        studentRepository.findByEmail(request.getEmail())
                .ifPresent(student -> {
                    throw new ConflictException(request.getEmail()+" already exists");
                });

        // convert DTO into entity
        Student student = Student.builder()
                .name(request.getName())
                .email(request.getEmail())
                .build();

        // save to DB
        Student savedStudent = studentRepository.save(student);

        // convert entity into DTO
        StudentResponseDTO response = mapToDTO(savedStudent);

        return new APIResponse<>(
          "Registration Successful",
                HttpStatus.CREATED,
                response
        );
    }




    // read single record
    public APIResponse<StudentResponseDTO> getEmail (
            String email
    ) {
        // checks if the student is present by email
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(
                        () -> new ResourceNotFoundException(email + " not found")
                );

        StudentResponseDTO dto = mapToDTO(student);
        return new APIResponse<>(
                "Record Found",
                HttpStatus.OK,
                dto
        );




    }


    // read all (pagination)
    public APIResponse<StudentListResponseDTO> getStudentList (
            int page, int size
    ) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Student> studentPage = studentRepository.findAll(pageable);

        List<StudentResponseDTO> list = studentPage.getContent()
                .stream()
                .map(this::mapToDTO)
                .toList();

        StudentListResponseDTO response =
                new StudentListResponseDTO(list, Math.toIntExact(((Long) studentPage.getTotalElements())));

        return new APIResponse<>(
                "Student List",
                HttpStatus.OK,
                response
        );
    }



    // update the existing record
    public APIResponse<StudentResponseDTO> updateStudent(
            String email, StudentRequestDTO request
    ) {
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(()-> new ResourceNotFoundException("no student with "+email));

        if (request.getName() != null && !request.getName().isEmpty()) {
            student.setName(request.getName());
        }

        if (request.getEmail() != null && !request.getEmail().isEmpty()) {
            student.setEmail(request.getEmail());
        }

        Student updatedStudent = studentRepository.save(student);

        StudentResponseDTO dto = mapToDTO(updatedStudent);
        return new APIResponse<>(
                "Student Updated",
                HttpStatus.OK,
                dto
        );
    }


    // delete the student
    public APIResponse<String> deleteStudent(String email) {

        Student student = studentRepository.findByEmail(email)
                .orElseThrow(()-> new ResourceNotFoundException("no student with "+email));

        studentRepository.delete(student);

        return new APIResponse<>(
                "student record deleted",
                HttpStatus.OK,
                "deleted "+student.getEmail()
        );
    }


    /*
     * Mapping method
     *
     * converts entity into DTO
     * why - we never expose entity directly and this method is reusable
     */

    private StudentResponseDTO mapToDTO (Student student) {
        StudentResponseDTO dto = new StudentResponseDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());

        return dto;
    }
}
