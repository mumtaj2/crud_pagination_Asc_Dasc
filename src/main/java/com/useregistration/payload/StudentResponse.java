package com.useregistration.payload;

import lombok.Data;

import java.util.List;

@Data
public class StudentResponse {

    private List<StudentDto> studentDto;
    private int pageNo;
    private int pageSize;
    private long totalElement;
    private int totalPages;
    private boolean last;
}
