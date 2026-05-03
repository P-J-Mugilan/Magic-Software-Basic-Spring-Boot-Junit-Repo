package com.magicsoftware.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Standard success response format good practice
 * Why
 *  - keep API response consistent
 *  - Include Http status for clarity
 */

@Data
@AllArgsConstructor
public class APIResponse <T>{

    private String message;
    private HttpStatus status;
    private T data;
}
