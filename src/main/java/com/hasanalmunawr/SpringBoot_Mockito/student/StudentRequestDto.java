package com.hasanalmunawr.SpringBoot_Mockito.student;

import lombok.Data;


public record StudentRequestDto(
         String firstName,
         String lastName,
         String gender
) {

}
