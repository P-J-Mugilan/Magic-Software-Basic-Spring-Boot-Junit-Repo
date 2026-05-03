package com.magicsoftware.dto;


import lombok.Data;

/**
 * Used when sending data back to client
 * Why - control what we return
 */

@Data
public class StudentResponseDTO {

    private Long id;
    private String name;
    private String email;
}
