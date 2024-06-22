package com.hasanalmunawr.SpringBoot_Mockito.student;


import org.springframework.stereotype.Service;

@Service
class StudentMapper {

    public Student dtoToEntity(StudentRequestDto requestDto) {
        Student student = new Student();
        student.setFirstName(requestDto.firstName());
        student.setLastName(requestDto.lastName());
        student.setGender(requestDto.gender());

        return student;
    }

    public StudentDto entityToDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setFirstName(student.getFirstName());
        studentDto.setLastName(student.getLastName());
        studentDto.setGender(student.getGender());

        return studentDto;
    }
}
