package com.magicsoftware.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Used when client SENDS data (POST)
 * Why - we don't expose entity directly
 */

@Data
public class StudentRequestDTO {

    /**
     * Name must not be empty
     * @NotBlank -> rejects null, empty, spaces
     */
    @NotBlank(message = "name cannot be empty")
    private String name;

    /**
     * Email must be valid format
     */
    @Email(message = "invalid email format")
    @NotBlank(message = "email cannot be empty")
    private String email;
}
