package com.magicsoftware.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Used for GET APIs returning list
 * Why - Helps frontend handle large data properly (Pagination Response) later
 */

@Data
@AllArgsConstructor
public class StudentListResponseDTO {

    private List<StudentResponseDTO> students;
    private Integer totalRecords;
}
