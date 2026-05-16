package org.example.de_7_2.service;


import org.example.de_7_2.entity.Student;
import org.example.de_7_2.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public boolean existsById (String id){
        return studentRepository.existsById(id);
    }
    @Override
    public void saveStudent(Student student){
        student.setApproved(0);
        studentRepository.save(student);
    }
}
