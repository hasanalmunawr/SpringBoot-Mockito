package com.hasanalmunawr.SpringBoot_Mockito.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveStudentSuccess() {
        // given
        StudentRequestDto requestDto = new StudentRequestDto(
                "hasan",
                "almunawar",
                "mele"
        );

        Student student = new Student(
                1,
                "hasan",
                "almunawar",
                "mele"
        );

        // when
        Mockito.when(studentService.save(requestDto))
                .thenReturn(student);
        // then

        Mockito.verify(studentMapper, times(1)) // FOR @Mock
                .dtoToEntity(requestDto);
//        Mockito.verify(studentRepository, times(1))
//                .save(student);


    }

    @Test
    void getAll() {
        List<Student> students = List.of(
                new Student(1,
                        "hasan",
                        "almunawar",
                        "mele"
        ),
                new Student(
                        2,
                        "husin",
                        "mu",
                        "mulet"
                )
        );
        studentRepository.saveAll(students);

        when(studentService.getAllStudents())
                .thenReturn(
                List.of(
                        new StudentDto(1,
                                "hasan",
                                "almunawar",
                                "mele"
                        ),
                        new StudentDto(
                                2,
                                "husin",
                                "mu",
                                "mulet"
                        )
                )
        );
    }

    @Test
    void getStudentById() {
        Student student = new Student(
                1,
                "hasan",
                "almunawar",
                "mele"
        );

        studentRepository.save(student);

        when(studentService.findById(1))
                .thenReturn(new StudentDto(
                                1,
                                "hasan",
                                "almunawar",
                                "mele"
                        )
                );
    }

    @Test
    void deleteById() {
        Student student = new Student(
                1,
                "hasan",
                "almunawar",
                "mele"
        );

        studentRepository.save(student);
    }
}