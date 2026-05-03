package com.magicsoftware.model;


import jakarta.persistence.*;
import lombok.*;

/**
 * Entity class
 * What - Represents a table in database
 * Why - JPA will convert this class into a table automatically
 */
@Entity


/*
 * @Table -> Optional but good practice
 * Why - explicitly define table name (implicitly create table name has class name i.e student is the table name)
 */
@Table (name = "student")

/*
 * Lombok annotations
 * @Getter - generates getters
 * @Setter - generates setters
 * @NoArgsConstructor - required by JPA
 * @AllArgsConstructor - useful for object creation
 * @Builder - clean object creation (Optional)
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    /**
     * primary key
     * @Id - marks as primary key
     * @GeneratedValue - auto increment
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Column mapping
     * nullable = false - cannot be null in DB
     */
    @Column(nullable = false)
    private String name;

    /**
     * unique = true - no duplicate emails allowed
     */
    @Column(nullable = false, unique = true)
    private String email;
}
