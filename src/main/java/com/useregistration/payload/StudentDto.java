package com.useregistration.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class StudentDto {
//
//    private Long id;                      ye sab normal ke liye hai niche jo hai wo Spring validation ka hai
//    private String name;
//    private String email;
//    private String address;
//

        private Long id;

        @NotEmpty(message = "Student name should not be empty")
        @Size(min = 2, message = "Student name should be at least 2 characters")
        private String name;

        @NotEmpty(message = "Email should not be empty")
        @Size(min = 4, message = "Email should be at least 4 characters")
        private String email;

        @NotEmpty(message = "Address should not be empty")
        @Size(min = 5, message = "Address should be at least 5 characters")
        private String address;

        // Getters and setters
    }
