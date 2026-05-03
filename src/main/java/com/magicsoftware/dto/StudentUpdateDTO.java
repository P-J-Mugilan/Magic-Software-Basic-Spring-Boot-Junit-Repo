package com.magicsoftware.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Used when client UPDATE data (PUT)
 * Why - Update doesn't always need all the fields like create
 */

@Data
@AllArgsConstructor
public class StudentUpdateDTO {

    private String name;
    private String email;
}
