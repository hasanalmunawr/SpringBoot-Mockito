package com.hasanalmunawr.SpringBoot_Mockito.student;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public Student save(StudentRequestDto requestDto) {
        var student = studentMapper.dtoToEntity(requestDto);
        return studentRepository.save(student);
    }

    public List<StudentDto> getAllStudents() {
        List<Student> students = (List<Student>) studentRepository.findAll();

        List<StudentDto> studentDtos = students.stream()
                .map(student -> studentMapper.entityToDto(student))
                .collect(Collectors.toList());

        return studentDtos;
    }

    public StudentDto findById(int id) {
        val student = studentRepository.findById(id).orElseThrow(null);
        return studentMapper.entityToDto(student);
    }

    public void deleteById(int id) {
        studentRepository.deleteById(id);
    }
}
