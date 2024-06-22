package com.hasanalmunawr.SpringBoot_Mockito.student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class StudentDto {

    private int id;
    private String firstName;
    private String lastName;
    private String gender;
}
